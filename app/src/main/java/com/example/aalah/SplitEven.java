package com.example.aalah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SplitEven extends AppCompatActivity {

    EditText numText;
    EditText priceText;
    float evenSplit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_even);


        Button calcBtn = findViewById(R.id.CalculateBtn);
        Button backBtn = findViewById(R.id.back2);



        backBtn.setOnClickListener(view ->{
            this.finish();
        });

        calcBtn.setOnClickListener(view -> {
            numText   = (EditText)findViewById(R.id.NumInput);
            priceText   = (EditText)findViewById(R.id.PriceInput);
            if (TextUtils.isEmpty(numText.getText().toString())||TextUtils.isEmpty(priceText.getText().toString())){
                Toast.makeText(SplitEven.this, "Please enter information!",
                        Toast.LENGTH_SHORT).show();
            }
            else{

                int num = Integer.parseInt(numText.getText().toString());
                float numberFloat = num;
                float price = Float.parseFloat(priceText.getText().toString());
                evenSplit = price/numberFloat;


                String result = String.format("%.2f",evenSplit);
                Intent intent = new Intent(this, SplitEvenInfo.class);

                Bundle extras = new Bundle();
                extras.putInt("personNum",num);
                extras.putFloat("totalPrice",price);

                intent.putExtras(extras);
                startActivity(intent);



            }





        });


    }}

