package com.example.abdulrahmanalshaghdali.letsunite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class Events_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Log.d("Switch","Event");
        //String re="invalid";
        TextView t3=(TextView) findViewById(R.id.textView3);

        BackgroundTasks asyncTask = new BackgroundTasks();

        //token place should be a token that identify the user himself
        asyncTask.execute("events/","token","PUT");
        try {
            String re = asyncTask.get(5, TimeUnit.SECONDS);
            t3.setText(re);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
