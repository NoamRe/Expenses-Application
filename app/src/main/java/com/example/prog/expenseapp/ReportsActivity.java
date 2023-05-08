package com.example.prog.expenseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        TextView sumTV = findViewById(R.id.sumReportsTV);
        TextView coinTypeTV = findViewById(R.id.coinTypeReportsTV);
        TextView dateTV = findViewById(R.id.dateReportsTV);
        TextView timeTV = findViewById(R.id.timeReportsTV);
        TextView expenseTypeTV = findViewById(R.id.expenseTypeReportsTV);
        TextView descriptionTV = findViewById(R.id.descriptionReportsTV);
        TextView commentsTV = findViewById(R.id.commentsReportsTV);
        MyDBHanlder myDBHanlder = new MyDBHanlder(this, null, null, 13);
        sumTV.setText(myDBHanlder.bigDBSumTS());
        coinTypeTV.setText(myDBHanlder.bigDBCoinTypeTS());
        dateTV.setText(myDBHanlder.bigDBDateTS());
        timeTV.setText(myDBHanlder.bigDBTimeTS());
        expenseTypeTV.setText(myDBHanlder.bigDBExpenseTypeTS());
        descriptionTV.setText(myDBHanlder.bigDBDescriptionTS());
        commentsTV.setText(myDBHanlder.bigDBCommentsTS());

    }
}
