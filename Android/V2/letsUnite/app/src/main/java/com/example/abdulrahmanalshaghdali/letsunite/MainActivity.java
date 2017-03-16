package com.example.abdulrahmanalshaghdali.letsunite;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;


public class MainActivity extends AppCompatActivity {

    private Button boerdBTN;
    private Button friendsBTN;
    private Button eventBTN;
    private Button newsBTN;
    private Switch switchBT;



    private TabHost th ;

    TextView tx;
    String text;
    ActionBar actionBar;
    private Handler handler;
    private String fname = "mydata";

    String user_id;
    String group_id;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //handler = new Handler();
        //handler.postDelayed(runnable,1000);


        try {
            FileInputStream fin = openFileInput(fname);
            InputStreamReader myReader = new InputStreamReader(fin);
            BufferedReader myBR = new BufferedReader(myReader);
            user_id = myBR.readLine();
            group_id = myBR.readLine();
            Log.d("user_id =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>",user_id);
            Log.d("group_id =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>",group_id);
            myBR.close();
            myReader.close();
            fin.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        boerdBTN = (Button) findViewById(R.id.boredBTN);
        boerdBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });



        eventBTN = (Button) findViewById(R.id.eventsBTN);
        eventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, " It is events ",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Events_Activity.class);
                startActivity(intent);
            }
        });

        newsBTN = (Button) findViewById(R.id.newsBT);
        newsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, " It is news ",
                        Toast.LENGTH_LONG).show();


                Intent intent = new Intent(MainActivity.this, GetActivity.class);

                startActivity(intent);

            }
        });

        switchBT = (Switch) findViewById(R.id.switchBT);
        switchBT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Log.v("========== = = = good to go = = = = ================", ""+isChecked);
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            //Put here
                            BackgroundTasks asyncTask = new BackgroundTasks();

                            //token place should be a token that identify the user himself
                            asyncTask.execute("events/",user_id,"PUT", Double.toString(Math.random()));
                            try {
                                String re = asyncTask.get(5, TimeUnit.SECONDS);
                                Log.d("Event data",re);

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            handler.postDelayed(this,5000);
                        }
                    };

                }else{
                    Log.v("========== = = = Stop Stop Stop..!! = = = = ================", ""+isChecked);
                }

            }
        });



    }//End OnCreate



    /*
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Put here
            BackgroundTasks asyncTask = new BackgroundTasks();

            //token place should be a token that identify the user himself
            asyncTask.execute("events/","shit","PUT", Double.toString(Math.random()));
            try {
                String re = asyncTask.get(5, TimeUnit.SECONDS);
                Log.d("Event data",re);

            }catch(Exception e){
                e.printStackTrace();
            }
            handler.postDelayed(this,5000);
        }
    };
    */

}