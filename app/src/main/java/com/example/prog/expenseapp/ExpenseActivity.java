package com.example.prog.expenseapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private TextView timeText, dateText;
    private int hours, minutes, day, month, year;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private EditText sumText, descriptionText, commentsText;
    private ArrayList<String> expenseTypeList, coinTypeListMain;
    private Spinner expenseTypeSpinner, coinTypeSpinnerMain;
    private ArrayAdapter expenseTypeListAdapter, coinTypeAdapter;
    private MyDBHanlder myDBHanlder;
    URL url;
    private OnlineStuff onlineStuff;

    @Override
    protected void onResume() {
        super.onResume();
        expenseTypeList = myDBHanlder.dataBaseToArrayList();
        if (expenseTypeList.contains("Food") && expenseTypeList.indexOf("Food") != 0) {
            expenseTypeList.remove("Food");
            expenseTypeList.add(0, "Food");
        }
        expenseTypeListAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, expenseTypeList);
        expenseTypeSpinner.setAdapter(expenseTypeListAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        sumText = findViewById(R.id.sumText);
        descriptionText = findViewById(R.id.descriptionText);
        commentsText = findViewById(R.id.commentsText);
        sumText.setFilters(new InputFilter[]{new InputFilterMinMax("0", "10000")});
        Calendar calendar = Calendar.getInstance();
        coinTypeSpinnerMain = findViewById(R.id.coinTypeSpinnerMain);
        timeText = findViewById(R.id.timeTV);
        dateText = findViewById(R.id.dateTV);
        hours = calendar.get(Calendar.HOUR_OF_DAY);
        minutes = calendar.get(Calendar.MINUTE);
        day = calendar.get(Calendar.DAY_OF_WEEK);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        timeSet(hours, minutes);
        dateSet(day, month, year);
        timePickerDialog = new TimePickerDialog(this, this, hours, minutes, true);
        datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        timeText.setOnClickListener(this);
        dateText.setOnClickListener(this);
        expenseTypeSpinner = findViewById(R.id.expenseTypeSpinner);
        myDBHanlder = new MyDBHanlder(this, null, null, 13);
        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            Log.d("Comments", "First time");
            myDBHanlder.addValues();
            settings.edit().putBoolean("my_first_time", false).commit();
        }

        expenseTypeList = myDBHanlder.dataBaseToArrayList();
        expenseTypeListAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, expenseTypeList);
        expenseTypeSpinner.setAdapter(expenseTypeListAdapter);

        try {
            url = new URL("http://data.fixer.io/api/latest?access_key=afe34a6a9164b0172c77769b10cd951c");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        coinTypeListMain = new ArrayList<String>();
        coinTypeAdapter = new ArrayAdapter(this, R.layout.expenetypelistadapter, R.id.expenseTypeListAdapterTV, coinTypeListMain);
        onlineStuff = new OnlineStuff(url, coinTypeSpinnerMain, coinTypeListMain, coinTypeAdapter);
        onlineStuff.execute();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.timeTV) {
            timePickerDialog.show();
        } else if (id == R.id.dateTV) {
            datePickerDialog.show();
        } else if (id == R.id.clearButton) {
            sumText.setText("");
            descriptionText.setText("");
            commentsText.setText("");
            timeSet(hours, minutes);
            dateSet(day, month, year);
        } else if (id == R.id.optionButton) {
            launchActivity();
        } else if (id == R.id.reportAnExpenseButton) {
            myDBHanlder.addExpenseReport(Double.parseDouble(sumText.getText().toString()), coinTypeSpinnerMain.getSelectedItem().toString(), dateText.getText().toString(), timeText.getText().toString(), expenseTypeSpinner.getSelectedItem().toString(), descriptionText.getText().toString(), commentsText.getText().toString());
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        dateSet(i2, i1 + 1, i);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        timeSet(i, i1);
    }

    public void timeSet(int hours, int minutes) {
        if (hours < 10) {
            if (minutes < 10) {
                timeText.setText("0" + hours + ":" + "0" + minutes);
            } else {
                timeText.setText("0" + hours + ":" + minutes);
            }
        } else if (minutes < 10) {
            timeText.setText(hours + ":" + "0" + minutes);
        } else {
            timeText.setText(hours + ":" + minutes);
        }
    }

    public void launchActivity() {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void dateSet(int day, int month, int year) {
        if (day < 10) {
            if (month < 10) {
                dateText.setText("0" + day + "/" + "0" + month + "/" + year);
            } else {
                dateText.setText("0" + day + "/" + minutes + "/" + year);
            }
        } else if (month < 10) {
            dateText.setText(day + "/" + "0" + month + "/" + year);
        } else {
            dateText.setText(day + "/" + month + "/" + year);
        }
    }


}
