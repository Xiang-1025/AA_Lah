package com.example.aalah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button evenSplitBtn = findViewById(R.id.EqualBtn);
        Button percentageSplitBtn = findViewById(R.id.percentageBtn);
        Button historyBtn = findViewById(R.id.HistoryBtn);

        evenSplitBtn.setOnClickListener(view -> {
            Intent splitEvenIntent = new Intent(
                    getApplicationContext(), SplitEven.class
            );
            startActivity(splitEvenIntent);
        });

        percentageSplitBtn.setOnClickListener(view -> {
            Intent splitPercentageIntent = new Intent(
                    getApplicationContext(), splitpercentage.class
            );
            startActivity(splitPercentageIntent);
        });

        historyBtn.setOnClickListener(view -> {
            Intent historyIntent = new Intent(
                    getApplicationContext(), History.class
            );
            startActivity(historyIntent);
        });



    }
}