package com.example.aalah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class History extends AppCompatActivity {

    private SQLiteAdapter SQLAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

                //create SQLiteAdapter
        SQLAd = new SQLiteAdapter(this);

        //Read Data
        SQLAd.openToRead();
        String contentRead = SQLAd.queueAll();
        TextView tv = findViewById(R.id.contentView);
        tv.setText(contentRead);

        Button backBtn = findViewById(R.id.backBtn5);
        Button clearBtn = findViewById(R.id.clearBtn);

        //add Scrollbar for the view
        tv.setMovementMethod(new ScrollingMovementMethod());

        //back to home page
        backBtn.setOnClickListener(view ->{
           this.finish();

        });

        //Clear the database and refresh
        clearBtn.setOnClickListener(view ->{
            SQLAd.deleteAll();
            finish();
            startActivity(getIntent());

        });



    }
}