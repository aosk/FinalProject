package com.example.abdulrahmanalshaghdali.letsunite;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boerdBTN;
    private Button friendsBTN;
    private Button eventBTN;
    private Button newsBTN;

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

        tx = (TextView) findViewById(R.id.textView);


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