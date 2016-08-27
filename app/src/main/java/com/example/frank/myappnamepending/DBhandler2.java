package com.example.frank.myappnamepending;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Frank on 8/23/2016.
 */
public class DBhandler2 extends SQLiteOpenHelper {

        public static final int database_version = 1;
        SQLiteDatabase SQ;

        //Create the table and store it in a string

        public String CREATE_QUERY = "CREATE TABLE " + TableData.HistoryTableInfo.TABLE_NAME_HISTORY + "(" + TableData.HistoryTableInfo.HISTORYDISTANCE + " REAL," + TableData.HistoryTableInfo.HISTORYMONEY + " REAL" + ");";

        public DBhandler2(Context context) {
            super(context, TableData.HistoryTableInfo.DATABASE_NAME_HISTORY, null, database_version);
            Log.d("DBHandler", "DataBase created Succesfully");
        }

        // Just like normal on create method

        @Override
        public void onCreate(SQLiteDatabase sdb) {

            // This actually takes the Query and makes a table

            sdb.execSQL(CREATE_QUERY);
            Log.d("DBHandler", "Table Created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

        }

        // This inserts data into the database

        public void putInformation(DBhandler2 dop,Double distance, Double moneySaved) {

            SQLiteDatabase SQ = dop.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TableData.HistoryTableInfo.HISTORYDISTANCE, distance);
            cv.put(TableData.HistoryTableInfo.HISTORYMONEY, moneySaved);
            long k = SQ.insert(TableData.HistoryTableInfo.TABLE_NAME_HISTORY, null, cv);
            SQ.close();
            Log.d("DBHandler", "One Row Inserted");
        }

        // Retrieve Data from database

        public Cursor getInformationHistory(DBhandler2 dop) {

            SQLiteDatabase SQ = this.getReadableDatabase();
            String[] colums = {TableData.HistoryTableInfo.HISTORYDISTANCE, TableData.HistoryTableInfo.HISTORYMONEY};
            Cursor CR = SQ.query(TableData.HistoryTableInfo.TABLE_NAME_HISTORY,colums, null, null,
                    null, null, null);
            return CR;

        }
    public void deleteDatabase() {

        SQLiteDatabase SQ = this.getWritableDatabase();
        SQ.delete(TableData.HistoryTableInfo.TABLE_NAME_HISTORY, null, null);
        SQ.close();

    }
}


