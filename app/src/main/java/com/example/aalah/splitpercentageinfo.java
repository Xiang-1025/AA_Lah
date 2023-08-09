package com.example.aalah;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class splitpercentageinfo extends AppCompatActivity {





    //Define ArrayList to store user input and calculated payment
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Float> percentage = new ArrayList<Float>();
    ArrayList<Float> pay = new ArrayList<Float>();
    private SQLiteAdapter SQLAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Passing number of person and total price from previous activity to current activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int personNum = extras.getInt("personNum");
        float price = extras.getFloat("totalPrice");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitpercentageinfo);

        Button nextBtn = findViewById(R.id.nextBtn);
        Button backBtn = findViewById(R.id.back);

        TextView tv = findViewById(R.id.personText);
        TextView display = findViewById(R.id.testView);
        display.setMovementMethod(new ScrollingMovementMethod());

        SQLAd = new SQLiteAdapter(this);


        backBtn.setOnClickListener(view->{
            this.finish();
        });

        Button home = findViewById(R.id.homeBtn2);

        home.setOnClickListener(view -> {
            Intent homeIntent = new Intent(
                    getApplicationContext(), MainActivity.class
            );
            startActivity(homeIntent);
        });


        tv.setText("Person "+1+"/"+personNum);
        display.setText("Total price: "+Float.toString(price)+"\nTotal person: "+(Integer.toString(personNum)));
        nextBtn.setOnClickListener(new View.OnClickListener() {

                int curNum=0;
                int validate=0;
                EditText nameText  = findViewById(R.id.nameInput);
                EditText percentageText= findViewById(R.id.percentageInput);





            public void onClick(View v){
                    //Once clicked, if current number of person is not equal to total number of person, execute
                display.setText("");
                    if (curNum<personNum){
                        float totalPercentage=0.00f;

                        //Avoid empty input field on name
                        if (TextUtils.isEmpty(nameText.getText().toString())){
                            Toast.makeText(splitpercentageinfo.this, "Please enter username!",
                            Toast.LENGTH_SHORT).show();
                        }
                        else{
                        name.add(nameText.getText().toString());
                        validate++;
                        }
                        //Avoid empty input field on percentage

                        if (TextUtils.isEmpty(percentageText.getText().toString())){
                            Toast.makeText(splitpercentageinfo.this, "Please enter percentage!",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            percentage.add(Float.parseFloat(percentageText.getText().toString()));
                            validate++;
                        }
                        //if both validation passed, execute
                        if (validate==2){
                            //add info into arraylist
                            pay.add((price*(percentage.get(curNum)/100)));

                            //Clear both input field
                            nameText.setText("");
                            percentageText.setText("");
                            curNum++;
                            tv.setText("Person "+(curNum+1)+"/"+personNum);

                            if (curNum==personNum){
                                for(int i=0;i<percentage.size();i++) {
                                    totalPercentage += percentage.get(i);
                                }
                                //If over 100% or lower than 100% after last person info entered
                                    if (totalPercentage > 100 || totalPercentage<100) {
                                        display.setText("Total percentage is "+totalPercentage+"\n Please Enter the Correct percentage!\n" +
                                                "The info has been reset,\n please enter the info from first person again.");
                                        curNum = 0;
                                        tv.setText("Person "+(curNum+1)+"/"+personNum);
                                        name.clear();
                                        pay.clear();
                                        percentage.clear();
                                        totalPercentage=0;
                                    }
                                    else{
                                        nextBtn.setText("Calculate");
                                        tv.setText("Click Home button to go back to Home page");
                                        display.setText("");
                                        for(int i=0;i<pay.size();i++) {
                                            display.append(name.get(i) + " need to pay " + percentage.get(i) + "% of RM" + price + ", which is RM" + pay.get(i) + "\n");
                                        }
                                        SQLAd.openToWrite();
                                        nextBtn.setVisibility(View.GONE);

                                        Date currentTime = Calendar.getInstance().getTime();
                                        SQLAd.insert("On "+currentTime.toString());
                                        SQLAd.insert(display.getText().toString());
                                        SQLAd.close();

                                    }

                            }
                            else{
                            for(int i=0;i<percentage.size();i++) {
                                totalPercentage += percentage.get(i);
                            }
                            //If over 100%
                                if (totalPercentage > 100) {
                                    display.setText("Total percentage is "+totalPercentage+"\n Please Enter the Correct percentage!\n The info has been reset,\n" +
                                            " Please enter the info from the first person again.");
                                    curNum = 0;
                                    tv.setText("Person "+(curNum+1)+"/"+personNum);
                                    //clear all data, restart
                                    name.clear();
                                    pay.clear();
                                    percentage.clear();
                                    totalPercentage=0;

                                }

                        }}
                        totalPercentage=0;

                        validate=0;



                    }


                }

            });


    }
}