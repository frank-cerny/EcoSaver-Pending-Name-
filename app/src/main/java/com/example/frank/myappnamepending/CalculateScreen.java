package com.example.frank.myappnamepending;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;
import java.io.*;

import org.w3c.dom.Text;

public class CalculateScreen extends AppCompatActivity {

    // Here I introduce all th variables I will be using in the App, they are mostly self
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

        // All this executes when the button is pressed, it checks to makes sure there
        // is input in each box then it grabs it and puts it in the variables
        // which we can then use to find the money saved and print it with setText()

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GasPrice.getText().toString().length() > 0) {
                    numGasPrice = Double.parseDouble(GasPrice.getText().toString());
                }
                if (MilesPerGallon.getText().toString().length() > 0) {
                    numMPG = Double.parseDouble(MilesPerGallon.getText().toString());
                }
                if (distance.getText().toString().length() > 0) {
                    numDistance = Double.parseDouble(distance.getText().toString());
                }
                moneySaved = (numDistance / numMPG) * numGasPrice;
                Double.toString(moneySaved);
                value = String.format("You Saved $ %.2f", moneySaved);
                Output.setText(value);
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
            }
        });
    }
}
