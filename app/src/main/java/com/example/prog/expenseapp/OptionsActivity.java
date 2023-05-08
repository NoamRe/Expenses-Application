package com.example.prog.expenseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity implements View.OnClickListener {
    Intent reportsIntent;
    Intent expensesIntent;
    Intent settingsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        reportsIntent = new Intent(this, ReportsActivity.class);
        expensesIntent = new Intent(this, ExpensesActivity.class);
        settingsIntent = new Intent(this, SettingsActivity.class);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.expensesButton) {
            startActivity(expensesIntent);
        } else if (id == R.id.settingsButton) {
            startActivity(settingsIntent);
        } else if (id == R.id.reportsButton) {
            startActivity(reportsIntent);
        }
    }
}
