package com.example.frank.myappnamepending;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                Output.setText("You Saved $" + Double.toString(moneySaved));

            }
        });
    }
}
