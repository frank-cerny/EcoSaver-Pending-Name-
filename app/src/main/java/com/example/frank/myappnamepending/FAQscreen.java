package com.example.frank.myappnamepending;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FAQscreen extends AppCompatActivity {

    private double totalMoneySaved;
    private double totalDistance;
    private double moneySaved;
    private double distance;
    private Button backToCalc;
    private Spinner MenuFaq;
    private TextView Output;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqscreen);

        backToCalc = (Button)findViewById(R.id.buttonBack);
        MenuFaq = (Spinner)findViewById(R.id.spinnerMenuFaq);
        Output = (TextView)findViewById(R.id.DBOutput);

        // Create an array adapter that allows me to input my own array into a spinner

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.FaqMenu_array,
                android.R.layout.simple_spinner_item);

        // Specify the layout when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the settings to the spinner

        MenuFaq.setAdapter(adapter);

        MenuFaq.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ChoiceFaq = parent.getItemAtPosition(position).toString();

                // An item was selected, now I get to change activities based on that

                if (ChoiceFaq.equalsIgnoreCase("Calculator")) {

                    Intent myIntent = new Intent(view.getContext(),CalculateScreen.class);
                    startActivity(myIntent);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        backToCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(v.getContext(),CalculateScreen.class);
                startActivity(myIntent);

            }
        });

        //////////////////////////// Database Stuff

        DatabaseOperations DOP = new DatabaseOperations(ctx);
        Cursor CR = DOP.getInformation(DOP);
        CR.moveToFirst();

        do {
            distance = CR.getDouble(0);
            moneySaved = CR.getDouble(1);

            totalDistance = totalDistance + distance;
            totalMoneySaved = totalMoneySaved + moneySaved;

        }while(CR.moveToNext());

        Output.setText("You traveled a total distance of " + totalDistance + "And saved a total of" +
                totalMoneySaved);
        ///////////////////////////
    }
}
