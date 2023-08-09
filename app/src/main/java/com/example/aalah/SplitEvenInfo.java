package com.example.aalah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SplitEvenInfo extends AppCompatActivity {
    private SQLiteAdapter SQLAd;

    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Float> pay = new ArrayList<Float>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int personNum = extras.getInt("personNum");
        float price = extras.getFloat("totalPrice");



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_even_info);

        Button nextBtn2 = findViewById(R.id.NextBtn2);
        Button backBtn = findViewById(R.id.back4);

        TextView tv2 = findViewById(R.id.TitleView);
        TextView display2 = findViewById(R.id.displayView);
        display2.setMovementMethod(new ScrollingMovementMethod());

        SQLAd = new SQLiteAdapter(this);

        tv2.setText("Person "+1+"/"+personNum);
        display2.setText("Total number of person: "+Integer.toString(personNum));

        //back to previous page
        backBtn.setOnClickListener( view ->{
            this.finish();
        });
        //Go to home page
        Button home = findViewById(R.id.homeBtn);

            home.setOnClickListener(view -> {
                Intent homeIntent = new Intent(
                        getApplicationContext(), Home.class
                );
                startActivity(homeIntent);
            });

        nextBtn2.setOnClickListener(new View.OnClickListener() {

            int curNum=0;
            int validate=0;
            EditText nameText  = findViewById(R.id.NameInput2);


            public void onClick(View v){
                //Once clicked, if current number of person is not equal to total number of person, execute
                display2.setText("");
                if (curNum<personNum){
                    float totalPercentage=0.00f;

                    //Avoid empty input field on name
                    if (TextUtils.isEmpty(nameText.getText().toString())){
                        Toast.makeText(SplitEvenInfo.this, "Please enter username!",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        name.add(nameText.getText().toString());
                        validate++;
                    }
                    //Avoid empty input field on percentage


                    }

                    //if validation passed, execute
                    if (validate==1){
                        //add info into arraylist
                        pay.add((price/personNum));
                        display2.append(name.get(curNum)+"\n");

                        //Clear both input field
                        nameText.setText("");
                        curNum++;
                        tv2.setText("Person "+(curNum+1)+"/"+personNum);

                        //if the current person is same to maximum person
                        if (curNum==personNum){

                                tv2.setText("Click Home button to go back to Home page");
                            display2.setText("");
                            for(int i=0;i<pay.size();i++) {
                                display2.append(name.get(i) + " need to pay RM" + pay.get(i) + "\n");




                            }
                            SQLAd.openToWrite();
                            nextBtn2.setVisibility(View.GONE);
                            Date currentTime = Calendar.getInstance().getTime();
                            SQLAd.insert("On "+currentTime.toString());
                            SQLAd.insert(display2.getText().toString());
                            SQLAd.close();
                        }



                    validate=0;

                }

            }

        });











    }
}