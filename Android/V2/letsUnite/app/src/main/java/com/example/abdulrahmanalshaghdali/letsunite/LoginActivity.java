package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


import static android.R.id.message;
import static java.net.Proxy.Type.HTTP;

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



                        BackgroundTasks asyncLoad = new BackgroundTasks();
                        asyncLoad.execute("login/", str);

                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(myIntent);

                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }







                /*
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String fixedEmail = "aa";

                // Verfication for Login.
                if(email.equals(fixedEmail))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                {
                   Toast.makeText(LoginActivity.this, "login failed)",
                           Toast.LENGTH_LONG).show();
                }
                */
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
