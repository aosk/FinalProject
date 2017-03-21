package com.example.abdulrahmanalshaghdali.letsunite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class Events_Activity extends AppCompatActivity {

    private String fname = "mydata";
    String user_id;
    String group_id;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Log.d(" - - - - - - -Switch- - - - - - -","Event");
        //String re="invalid";
        TextView t3=(TextView) findViewById(R.id.textView3);
        Bundle bundle = getIntent().getExtras();


        t3.setText(bundle.getString("events"));

    }

}
