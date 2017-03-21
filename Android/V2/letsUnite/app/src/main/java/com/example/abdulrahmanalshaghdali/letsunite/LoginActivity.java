package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button singinBTN;
    private Button signupBTN;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("Login works", "This is the login Activity.");
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        // password
        mPasswordView = (EditText) findViewById(R.id.password);
        singinBTN = (Button) findViewById(R.id.email_sign_in_button);
        singinBTN.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                String emailLoginCheck = mEmailView.getText().toString();
                String passwordLoginCheck = mPasswordView.getText().toString();

                if(emailLoginCheck.equals("") || passwordLoginCheck.equals("")) {

                    Toast.makeText(LoginActivity.this, "You are funny, Enter Email & Password",
                            Toast.LENGTH_LONG).show();
                }else{

                    try {
                        JSONObject postData = new JSONObject();
                        postData.put("email", mEmailView.getText().toString());
                        postData.put("password", mPasswordView.getText().toString());

                        String str = postData.toString();
                        String respond="";


                        BackgroundTasks asyncLoad = new BackgroundTasks();
                        asyncLoad.execute("login/", str, "POST");
                        respond = asyncLoad.get(5,TimeUnit.SECONDS);
                        Log.d("=-=-=-=-=-=-=--=-=-=>",respond);
                        if(respond.equals("200")) {
                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            LoginActivity.this.startActivity(myIntent);
                        }else if(respond.equals("400")){
                            Toast.makeText(LoginActivity.this, "You are not united !! Signup Plzz",
                                    Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(LoginActivity.this, "Check your internet connection dude !!",
                                    Toast.LENGTH_LONG).show();
                        }

                    } catch(JSONException e){
                        e.printStackTrace();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        signupBTN = (Button) findViewById(R.id.signUp);

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();





    }
}
