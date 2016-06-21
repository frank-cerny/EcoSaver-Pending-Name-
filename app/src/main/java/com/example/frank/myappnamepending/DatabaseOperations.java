package com.example.frank.myappnamepending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.frank.myappnamepending.TableData.TableInfo;

/**
 * Created by Frank on 6/20/2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version = 1;

    //Create the table and store it in a string

    public String CREATE_QUERY = "CREATE TABLE "+TableInfo.TABLE_NAME+"("+TableInfo.DISTANCE+" REAL,"+TableInfo.MONEY_SAVED+" REAL );";

    public DatabaseOperations(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "DataBase created Succesfully");
    }

    // Just like normal on create method

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        // This actually takes the Query and makes a table

        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    // This inserts data into the database

    public void putInformation(DatabaseOperations dop,Double distance, Double moneySaved) {

        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.DISTANCE, distance);
        cv.put(TableInfo.MONEY_SAVED, moneySaved);
        long k = SQ.insert(TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One Row Inserted");
    }

    // Retrieve Data from database

    public Cursor getInformation(DatabaseOperations dop) {

        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] colums = {TableInfo.DISTANCE, TableInfo.MONEY_SAVED};
        Cursor CR = SQ.query(TableInfo.TABLE_NAME,colums, null, null,
                null, null, null);
        return CR;

    }
}
