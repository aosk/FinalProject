package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class database extends AppCompatActivity {

    private TextView view1;
    private Button but1;
    private EditText edit1;


    //Firebase Test ...
    // This is to test viewing data from Firebase DB
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference conditionRef = mRootRef.child("condition");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);




        view1 = (TextView)findViewById(R.id.view11);
        but1 = (Button)findViewById(R.id.but11);
        edit1 = (EditText)findViewById(R.id.edit11);
    }

    @Override
    protected void onStart() {
        super.onStart();

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                view1.setText(text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                conditionRef.setValue(edit1.getText().toString());
                //conditionRef.setValue("testat");


            }
        });
    }
}
