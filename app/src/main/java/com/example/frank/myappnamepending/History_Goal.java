package com.example.frank.myappnamepending;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class History_Goal extends AppCompatActivity {

    public static final String PREFS = "examplePrefs";

    private double totalDistance;
    private double totalMoneySaved;
    private double HistorytotalMoneySaved;
    private double HistorytotalDistance;
    private double moneySaved;
    private double distance;
    private Spinner MenuHistory;
    private TextView HistoryOutput;
    private TextView GoalOutput;
    private Button NewGoal;
    private float GoalValue;
    private boolean bool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history__goal);

        HistoryOutput = (TextView)findViewById(R.id.textViewHistory);
        NewGoal = (Button)findViewById(R.id.buttonNewGoal);
        MenuHistory = (Spinner)findViewById(R.id.MenuHistory);
        GoalOutput = (TextView)findViewById(R.id.textViewGoal);

        // Create an array adapter that allows me to input my own array into a spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.HistoryMenu_array,
                android.R.layout.simple_spinner_item);

        // Specify the layout when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the settings to the spinner

        MenuHistory.setAdapter(adapter);

        MenuHistory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Choice = parent.getItemAtPosition(position).toString();

                // An item was selected, now I get to change activities based on that

                if (Choice.equalsIgnoreCase("FAQ")) {

                    Intent myIntent = new Intent(view.getContext(), FAQscreen.class);
                    startActivity(myIntent);
                } else if (Choice.equalsIgnoreCase("Calculator")) {

                    Intent myIntent = new Intent(view.getContext(), CalculateScreen.class);
                    startActivity(myIntent);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Shared Preferences Stuff

        final SharedPreferences examplePrefs = getSharedPreferences(PREFS, 0);
        final SharedPreferences.Editor editor = examplePrefs.edit();

        SharedPreferences get = getSharedPreferences(PREFS, 0);

        float userGoalShared = examplePrefs.getFloat("message",0);

        if (userGoalShared > 0) {
            GoalOutput.setText("You currently have a goal of $ " + userGoalShared);
        }
        else {
            GoalOutput.setText("No current goal, you should set one!");
        }

        Log.d("SharedPreferneces", "" + userGoalShared);

        // Create a Helper Object

        final HelperMethods Helper = new HelperMethods();

        NewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // All Database Info is wiped here, mostly for debugging purposes and resetting goal

                DatabaseOperations SQ = new DatabaseOperations(getApplicationContext());
                SQ.deleteDatabase();

                // Show alert dialogue and get a goal value

               GoalValue = Helper.getYesNoWithExecutionStop("GoalSetter","Set a Goal Please", History_Goal.this);
               GoalOutput.setText("Your new goal is $ " + GoalValue);
                editor.putFloat("message", GoalValue);
                editor.apply();
                Log.d("RightAfterMethod", "" + GoalValue);

            }
        });

        Log.d("HelperMethod", "" + Helper.GetGoal());

     /////////////////////////////////////// Database Stuff //////////////////////////////////////

        DatabaseOperations DOP = new DatabaseOperations(History_Goal.this);
        Cursor CR = DOP.getInformation(DOP);

        // Check if the database exists first so that it doesn't return null

        if (CR.moveToFirst()) {

            CR.moveToFirst();

            do {
                distance = CR.getDouble(0);
                moneySaved = CR.getDouble(1);

                totalDistance = totalDistance + distance;
                totalMoneySaved = totalMoneySaved + moneySaved;

            } while (CR.moveToNext());

            HistorytotalDistance = totalDistance;
            HistorytotalMoneySaved = totalMoneySaved;

            String HistoryDistanceFormatted = String.format("You traveled a totlal distance of %.2f", HistorytotalDistance);
            String HistorySavedFormatted = String.format("And saved a total of %.2f", HistorytotalMoneySaved);
            String GoalMoneyFormatted = String.format("You have saved %.2f", totalMoneySaved) +
                    ("out of your " +
                            "goal of " + GoalValue + "Which is " + totalMoneySaved / GoalValue + "%");

            HistoryOutput.setText(HistoryDistanceFormatted + " " + HistorySavedFormatted);
            GoalOutput.setText(GoalMoneyFormatted);

            ///////////////////////////////////////////////////////////////////////////////////
        }
    }
}


