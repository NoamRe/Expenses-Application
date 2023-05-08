package com.example.prog.expenseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Noam on 07/05/2018.
 */

public class MyDBHanlder extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "expenseTypes.db";
    public static final String EXPENSE_TYPES = "expenses";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EXPENSETYPENAME = "_expenseTypeName";
    public static final String BIG_TABLE_EXPENSES = "expensesReports";
    public static final String BIG_COLUMN_ID = "_id";
    public static final String SUM_COLUMN = "_sum";
    public static final String COIN_TYPE_COLUMN = "_coinType";
    public static final String DATE_COLUMN = "_date";
    public static final String TIME_COLUMN = "_time";
    public static final String EXPENSE_TYPE_BIG_TABLE = "_expenseType";
    public static final String DESCRIPTION_COLUMN = "_description";
    public static final String COMMENTS_COLUMN = "_comments";

    public MyDBHanlder(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE " + EXPENSE_TYPES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSETYPENAME + " TEXT " +
                ");";
        String query2 = "CREATE TABLE " + BIG_TABLE_EXPENSES + "(" +
                BIG_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUM_COLUMN + " DOUBLE, " +
                COIN_TYPE_COLUMN + " TEXT, " +
                DATE_COLUMN + " TEXT, " +
                TIME_COLUMN + " TEXT, " +
                EXPENSE_TYPE_BIG_TABLE + " TEXT, " +
                DESCRIPTION_COLUMN + " TEXT, " +
                COMMENTS_COLUMN + " TEXT " +
                ");";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TYPES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BIG_TABLE_EXPENSES);
        onCreate(sqLiteDatabase);
    }

    public void addValues() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        values.put(COLUMN_EXPENSETYPENAME, "General");
        values1.put(COLUMN_EXPENSETYPENAME, "Car");
        values2.put(COLUMN_EXPENSETYPENAME, "Food");
        values3.put(COLUMN_EXPENSETYPENAME, "House");
        values4.put(COLUMN_EXPENSETYPENAME, "Leisure");
        values5.put(COLUMN_EXPENSETYPENAME, "Clothes");
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values);
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values1);
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values2);
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values3);
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values4);
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values5);
    }

    public void addExpenseType(String expenseTypes) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXPENSETYPENAME, expenseTypes);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(EXPENSE_TYPES, null, values);

    }

    public void deleteExpenseType(String expenseType) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + EXPENSE_TYPES + " WHERE " + COLUMN_EXPENSETYPENAME + "=\"" + expenseType + "\";");

    }

    public void addExpenseReport(double sum, String coinType, String date, String time, String expenseType, String description, String comments) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        ContentValues values6 = new ContentValues();
        values.put(SUM_COLUMN, sum);
        values.put(COIN_TYPE_COLUMN, coinType);
        values.put(DATE_COLUMN, date);
        values.put(TIME_COLUMN, time);
        values.put(EXPENSE_TYPE_BIG_TABLE, expenseType);
        values.put(DESCRIPTION_COLUMN, description);
        values.put(COMMENTS_COLUMN, comments);
        sqLiteDatabase.insert(BIG_TABLE_EXPENSES, null, values);

    }

    public String bigDBCommentsTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String comments = "";
        while (!c.isAfterLast()) {
            comments += "-----" + c.getString(c.getColumnIndex(COMMENTS_COLUMN));
            comments += "\n";
            c.moveToNext();
        }
        c.close();
        return comments;
    }

    public String bigDBDescriptionTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String descriptions = "";
        while (!c.isAfterLast()) {
            descriptions += "-----" + c.getString(c.getColumnIndex(DESCRIPTION_COLUMN));
            descriptions += "\n";
            c.moveToNext();
        }
        c.close();
        return descriptions;
    }

    public String bigDBExpenseTypeTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String expenseTypes = "";
        while (!c.isAfterLast()) {
            expenseTypes += "-----" + c.getString(c.getColumnIndex(EXPENSE_TYPE_BIG_TABLE));
            expenseTypes += "\n";
            c.moveToNext();
        }
        c.close();
        return expenseTypes;
    }

    public String bigDBTimeTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String times = "";
        while (!c.isAfterLast()) {
            times += "-----" + c.getString(c.getColumnIndex(TIME_COLUMN));
            times += "\n";
            c.moveToNext();
        }
        c.close();
        return times;
    }

    public String bigDBDateTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String dates = "";
        while (!c.isAfterLast()) {
            dates += "-----" + c.getString(c.getColumnIndex(DATE_COLUMN));
            dates += "\n";
            c.moveToNext();
        }
        c.close();
        return dates;
    }

    public String bigDBCoinTypeTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String coinTypes = "";
        while (!c.isAfterLast()) {
            coinTypes += "-----" + c.getString(c.getColumnIndex(COIN_TYPE_COLUMN));
            coinTypes += "\n";
            c.moveToNext();
        }
        c.close();
        return coinTypes;
    }

    public String bigDBSumTS() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + BIG_TABLE_EXPENSES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        String sums = "";
        while (!c.isAfterLast()) {

            sums += "-----" + c.getDouble(c.getColumnIndex(SUM_COLUMN));
            sums += "\n";
            c.moveToNext();
        }
        c.close();
        return sums;
    }

    public ArrayList<String> dataBaseToArrayList() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + EXPENSE_TYPES;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();
        ArrayList<String> expenseTypesArrayList = new ArrayList<String>();
        while (!c.isAfterLast()) {

            expenseTypesArrayList.add(c.getString(c.getColumnIndex(COLUMN_EXPENSETYPENAME)));


            c.moveToNext();
        }
        c.close();

        return expenseTypesArrayList;
    }
}
