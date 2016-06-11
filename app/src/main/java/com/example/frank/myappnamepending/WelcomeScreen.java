package com.example.frank.myappnamepending;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    private Button ActivitySwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        ActivitySwitch = (Button)findViewById(R.id.buttonToCalc);


        // This checks within system memory to see whether or not the app has been opened
        // if yes then it automatically goes to the calculator, if no it allows
        // the user to see the welcome screen and then it sets the executed value to true

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, CalculateScreen.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }

        ActivitySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Intents are messages between activities and we create
                // and listen for one here, we use intents to jump
                // between activities with a simple button press

                Intent myIntent = new Intent(v.getContext(),CalculateScreen.class);
                startActivity(myIntent);

            }
        });
    }
}
