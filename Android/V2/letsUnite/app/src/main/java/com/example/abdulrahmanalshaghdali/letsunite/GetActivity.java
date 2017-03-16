package com.example.abdulrahmanalshaghdali.letsunite;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.concurrent.TimeUnit;

public class GetActivity extends AppCompatActivity {

    TextView tx ;
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        tx = (TextView)findViewById(R.id.textView2);
        btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Switch","News");
                //String re="invalid";

                BackgroundTasks asyncTask = new BackgroundTasks();

                //token place should be a token that identify the user himself
                asyncTask.execute("news/","token","PUT");
                Toast.makeText(GetActivity.this, " News are Requested !! " ,
                        Toast.LENGTH_LONG).show();
                try {
                    String re = asyncTask.get(5, TimeUnit.SECONDS);
                    tx.setText(re);

                }catch(Exception e){
                    e.printStackTrace();
                }


            }
        });
    }


}
