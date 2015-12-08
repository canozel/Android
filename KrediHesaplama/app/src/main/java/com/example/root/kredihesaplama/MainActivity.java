package com.example.root.kredihesaplama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void intentNoDataActivity(View view) {
        Intent intent = new Intent(this, NoDataCalculate.class);
        startActivity(intent);
    }

    public void intentDataActivity(View view) {
        Intent intent = new Intent(this, DataCalculate.class);
        startActivity(intent);
    }
}
