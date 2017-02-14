package com.example.abdulrahmanalshaghdali.letsunite;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private Button boerdBTN;
    private Button friendsBTN;
    private Button eventBTN;
    private Button newsBTN;

    private TabHost th ;

    TextView tx;
    String text;
    ActionBar actionBar;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        th = (TabHost) findViewById(R.id.tabs2);
        th.setup();

        TabHost.TabSpec ts1 = th.newTabSpec("tag1");
        ts1.setIndicator("Bored");
        ts1.setContent(R.id.bored);

        th.addTab(ts1);

        TabHost.TabSpec ts2 = th.newTabSpec("tag2");
        ts2.setIndicator("Friends");
        ts2.setContent(R.id.friends);
        th.addTab(ts2);

        TabHost.TabSpec  ts3 = th.newTabSpec("tag3");
        ts3.setIndicator("Events");
        ts3.setContent(R.id.events);
        th.addTab(ts3);

        TabHost.TabSpec  ts4 = th.newTabSpec("tag4");
        ts4.setIndicator("News");
        ts4.setContent(R.id.news);
        th.addTab(ts4);
        //tx = (TextView) findViewById(R.id.textView);
*/

        boerdBTN = (Button) findViewById(R.id.boredBTN);
        boerdBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });

        friendsBTN = (Button) findViewById(R.id.friendsBTN);
        friendsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, " posted to the server !! ",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, Location_Activity.class);
                startActivity(intent);
            }
        });


        eventBTN = (Button) findViewById(R.id.eventsBTN);
        eventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, " It is events ",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, database.class);
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



    }//End OnCreate

}