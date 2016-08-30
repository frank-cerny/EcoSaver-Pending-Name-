package com.example.frank.myappnamepending;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Frank on 7/6/2016.
 */

public class HelperMethods {

    private float GoalValue;
    double Goal;
    double moneyHistory;
    double distanceHistory;

    public float getYesNoWithExecutionStop(String title, final String message, Context context) {
        // make a handler that throws a runtime exception when a message is recieved
        final Handler handler = new Handler() {
            @Override
                    public void handleMessage(Message mesg) {
                throw new RuntimeException();
            }
        };

        // make a text input dialog and show it
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        alert.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.editText);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GoalValue = Float.parseFloat(editText.getText().toString());
                handler.sendMessage(handler.obtainMessage());
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handler.sendMessage(handler.obtainMessage());
            }
        });
        alert.show();

        // loop till a runtime exception is triggered
        try {
            Looper.loop();}
            catch (RuntimeException e2) {}

        return GoalValue;
    }

    public void SetHistory(Double HistoryMoney, Double HistoryDistance) {

        this.distanceHistory = HistoryDistance;
        this.moneyHistory = HistoryMoney;


    }
    public String GetHistoryString () {

        String HistoryOutput = ("You have traveled a total distance of " + this.distanceHistory +
        " and saved a total of $ " + this.moneyHistory);

        return HistoryOutput;
    }
    public double GetHistoryMoney () {

        Log.d("InsideGetHistoryMoney","" + moneyHistory);
        return moneyHistory;
    }
    public double GetHistoryDistance () {

        return distanceHistory;
    }
}