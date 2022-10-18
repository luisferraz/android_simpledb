package br.ufpr.tads.luis.simpledb.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import br.ufpr.tads.luis.simpledb.R;

public class MainActivity extends AppCompatActivity {
    private StudentOperations studentDBOperations;
    ListView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDBOperations = new StudentOperations();

    }

    @Override
    protected void onPause() {
        super.onPause();
        studentDBOperations.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentDBOperations.open();
    }
}