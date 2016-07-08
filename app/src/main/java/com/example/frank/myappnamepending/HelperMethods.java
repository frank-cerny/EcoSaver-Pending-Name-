package com.example.frank.myappnamepending;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Frank on 7/6/2016.
 */

public class HelperMethods {

    // This method will ask for a user goal with a pop up text box, and will return the value
    // to the database and use it on the Goal Screen

    // Define the main variable, should probably use Get and Set methods...
    // THIS VARIABLE IS GLOBAL AND THE INNER METHOD CANNOT SET VALUE THATS THE ISSUE

    double Goal = 0.0;

    public double SetGoal(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Setting up how the pop up is going to look using external layout file

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        // builder.setTitle("Title");
        builder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText);

        // set up dialogue window and the buttons

        builder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    // If Ok Button is clicked, take value from box

                    public void onClick(DialogInterface dialog, int which) {

                        // This correctly sets Goal to value of text box

                        Goal = Double.parseDouble(editText.getText().toString());

                        if (Goal > 0) {
                            Log.d("AlertDialogue", "Ok Button Clicked and worked");
                            Log.d("AlertDialogue", "" + Goal);
                        }
                        else {
                            Log.d("AlertDialogue", "Crap its 0....");
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("AlertDialogue", "Cancel Button Clicked");
                                dialog.cancel();
                            }
                        });

        // Actually Create the Alert Box

        AlertDialog dialog = builder.create();
        dialog.show();
        Log.d("AlertDialogue", "PopUp Shows up" + Goal);

        return Goal;
    }
}