package com.example.aalah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SplitPercentage extends AppCompatActivity {
    EditText numText;
    EditText priceText;
    float evenSplit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitpercentage);



        Button calcBtn = findViewById(R.id.CalculateBtn2);
        Button backBtn = findViewById(R.id.back3);

        backBtn.setOnClickListener(view->{
            this.finish();
        });




        calcBtn.setOnClickListener(view -> {


            numText   = (EditText)findViewById(R.id.NumInput);
            priceText   = (EditText)findViewById(R.id.PriceInput);

            if (TextUtils.isEmpty(numText.getText().toString())||TextUtils.isEmpty(priceText.getText().toString())){
                Toast.makeText(SplitPercentage.this, "Please enter information!",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                int num = Integer.parseInt(numText.getText().toString());
                float price = Float.parseFloat(priceText.getText().toString());
                Intent intent = new Intent(this, SplitPercentageInfo.class);
                Bundle extras = new Bundle();
                extras.putInt("personNum", num);
                extras.putFloat("totalPrice", price);
                intent.putExtras(extras);
                startActivity(intent);
            }


        });






    }
}