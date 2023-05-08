package com.example.prog.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> expenseTypeList;
    private ArrayAdapter coinTypeAdapter;
    private ArrayList<String> coinTypeList;
    private Spinner spinner, coinSpinner;
    private MyDBHanlder myDBHanlder;
    private ArrayAdapter arrayAdapter;
    private EditText editText;
    URL url;
    private OnlineStuff onlineStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner = findViewById(R.id.spinnerRemoveExpenseType);
        coinSpinner = findViewById(R.id.coinTypeSpinner);
        editText = findViewById(R.id.addExpenseTypeEditText);
        myDBHanlder = new MyDBHanlder(this, null, null, 13);
        expenseTypeList = myDBHanlder.dataBaseToArrayList();
        if (expenseTypeList.contains("Food") && expenseTypeList.indexOf("Food") != 0) {
            expenseTypeList.remove("Food");
            expenseTypeList.add(0, "Food");
        }
        arrayAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, expenseTypeList);
        spinner.setAdapter(arrayAdapter);
        try {
            url = new URL("http://data.fixer.io/api/latest?access_key=afe34a6a9164b0172c77769b10cd951c");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        coinTypeList = new ArrayList<String>();
        coinTypeAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, coinTypeList);
        onlineStuff = new OnlineStuff(url, coinSpinner, coinTypeList, coinTypeAdapter);
        onlineStuff.execute();


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.removeExpenseTypeButton) {

            myDBHanlder.deleteExpenseType(spinner.getSelectedItem().toString());
            expenseTypeList = myDBHanlder.dataBaseToArrayList();
            arrayAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, expenseTypeList);
            spinner.setAdapter(arrayAdapter);
        } else if (view.getId() == R.id.addExpenseTypeButton) {
            myDBHanlder.addExpenseType(editText.getText().toString());
            editText.setText("");
            expenseTypeList = myDBHanlder.dataBaseToArrayList();
            arrayAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, expenseTypeList);
            spinner.setAdapter(arrayAdapter);


        } else if (view.getId() == R.id.coinTypeButton) {

        }
    }


}
