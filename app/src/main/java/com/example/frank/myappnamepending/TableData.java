package com.example.frank.myappnamepending;

import android.provider.BaseColumns;

/**
 * Created by Frank on 6/20/2016.
 */
public class TableData {

    public TableData() {

    }

    public static class TableInfo implements BaseColumns {

        // Names of columns and db/table name

        public static final String DISTANCE = "distance";
        public static final String MONEY_SAVED = "moneySaved";
        public static final String DATABASE_NAME = "AppDB";
        public static final String TABLE_NAME = "calculatorData";
    }

    public static class HistoryTableInfo implements BaseColumns {
        public static final String HISTORYMONEY = "historyMoneySaved";
        public static final String HISTORYDISTANCE = "historyDistance";
        public static final String DATABASE_NAME_HISTORY = "historicValues";
        public static final String TABLE_NAME_HISTORY = "historicData";
    }
}
