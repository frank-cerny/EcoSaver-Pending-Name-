package com.example.frank.myappnamepending;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FAQscreen extends AppCompatActivity {

    private Button backToCalc;
    private Spinner MenuFaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqscreen);

        backToCalc = (Button)findViewById(R.id.buttonBack);
        MenuFaq = (Spinner)findViewById(R.id.spinnerMenuFaq);

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
    }
}
