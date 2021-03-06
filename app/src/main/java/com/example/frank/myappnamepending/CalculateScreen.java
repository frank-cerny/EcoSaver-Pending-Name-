package com.example.frank.myappnamepending;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.GenericArrayType;

public class CalculateScreen extends AppCompatActivity {

    // Here I introduce all the variables I will be using in the App, they are mostly self
    // explanatory.

    private double numGasPrice;
    private double numMPG;
    private double numDistance;
    private double moneySaved;
    Button btnCalculate;
    private EditText GasPrice;
    private EditText MilesPerGallon;
    private EditText distance;
    private TextView Output;
    private Button btnReset;
    private String value;
    private TextView error;
    private Button UnitSwitch;
    private String Unit;
    private TextView CarEfficiency;
    private TextView TextGasPrice;
    private TextView Distance;
    private String currentUnit = "US";
    private Button toFAQ;
    private Spinner Menu;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_screen);

        // Initialize the variables that take the value of the given input on the screen

        GasPrice = (EditText)findViewById(R.id.Price);
        MilesPerGallon = (EditText)findViewById(R.id.MPG);
        distance = (EditText)findViewById(R.id.Distance);
        Output = (TextView)findViewById(R.id.savedMoney);
        btnCalculate = (Button)findViewById(R.id.buttonCalculate);
        btnReset = (Button)findViewById(R.id.btn_reset);
        error = (TextView)findViewById(R.id.errorMessage);
        UnitSwitch = (Button)findViewById(R.id.unitSwitch);
        CarEfficiency = (TextView)findViewById(R.id.textViewMPG);
        TextGasPrice = (TextView)findViewById(R.id.textViewPrice);
        Distance = (TextView)findViewById(R.id.textViewDistance);
        toFAQ = (Button)findViewById(R.id.buttonFaq);
        Menu = (Spinner) findViewById(R.id.spinnerMenu);

        // Create an array adapter that allows me to input my own array into a spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.MainMenu_array,
                android.R.layout.simple_spinner_item);

        // Specify the layout when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the settings to the spinner

        Menu.setAdapter(adapter);

        Menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Choice = parent.getItemAtPosition(position).toString();

                // An item was selected, now I get to change activities based on that

                if (Choice.equalsIgnoreCase("FAQ")) {

                    Intent myIntent = new Intent(view.getContext(),FAQscreen.class);
                    startActivity(myIntent);
                }
                else if (Choice.equalsIgnoreCase("Goal/History")) {

                    Intent myIntent = new Intent(view.getContext(),History_Goal.class);
                    startActivity(myIntent);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
        }
});
        // This ensures that other countries can use the app, this changes all prompts
        // into a KM and liter based system, the button acts as a toggle, only EU for now

        UnitSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentUnit = UnitSwitch.getText().toString();

                if (currentUnit.equalsIgnoreCase("To INT")) {
                    UnitSwitch.setText("To US");
                    currentUnit = "INT";
                    TextGasPrice.setText("Gas Price Per Liter");
                    CarEfficiency.setText("KM/Liter of Main car");
                    Distance.setText("Distance in KM");
                }
                else {
                    UnitSwitch.setText("To INT");
                    currentUnit = "US";
                    TextGasPrice.setText("Gas Price Per Gallon");
                    CarEfficiency.setText("MPG of Main Car");
                    Distance.setText("Distance in Miles");
                }
            }
        });

        // All this executes when the button is pressed, it checks to makes sure there
        // is input in each box then it grabs it and puts it in the variables
        // which we can then use to find the money saved and print it with setText()

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (GasPrice.getText().toString().length() > 0 && MilesPerGallon.getText().toString().length() > 0
                        && distance.getText().toString().length() > 0) {

                    error.setText("");

                    numGasPrice = Double.parseDouble(GasPrice.getText().toString());
                    numMPG = Double.parseDouble(MilesPerGallon.getText().toString());
                    numDistance = Double.parseDouble(distance.getText().toString());

                    moneySaved = (numDistance / numMPG) * numGasPrice;

                    //////////////////////////// Database Insertion

                    DBhandler2 AR = new DBhandler2(ctx);
                    DatabaseOperations DB = new DatabaseOperations(ctx);
                    DB.putInformation(DB, numDistance, moneySaved);
                    AR.putInformation(AR,numDistance, moneySaved);


                    ////////////////////////////

                    Double.toString(moneySaved);

                    // This converts the output saying to Euros for better accessibility of
                    // international countries

                    if (currentUnit.equalsIgnoreCase("US")) {
                        value = String.format("You Saved $ %.2f", moneySaved);
                    }
                    else {
                        value = String.format("You Saved € %.2f", moneySaved);
                    }
                    Output.setText(value);
                }

                // This is a basic validation to make sure that the user entered values
                // in all three boxes, if not it will prompt them to retry and enter values

                else {
                    error.setText(R.string.errorMessage);
                }

            }
        });

        // This handles the reset button, when clicked all user entered data is erased
        // in order to put in more numbers easier

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numGasPrice = 0;
                numMPG = 0;
                numDistance = 0;
                GasPrice.setText("");
                MilesPerGallon.setText("");
                distance.setText("");
                Output.setText("");
                error.setText("");
            }
        });

        toFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(),FAQscreen.class);
                startActivity(myIntent);

            }
        });
    }
}
