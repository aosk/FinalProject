package com.example.abdulrahmanalshaghdali.letsunite;

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
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.id.message;
import static java.net.Proxy.Type.HTTP;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    String data = "";

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

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        // password
        mPasswordView = (EditText) findViewById(R.id.password);
        singinBTN = (Button) findViewById(R.id.email_sign_in_button);
        singinBTN.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String server = "http://54.191.242.216:8000/";
                String local = "http://10.0.2.2:8000/login/";

                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", mEmailView.getText().toString());
                    postData.put("password", mPasswordView.getText().toString());

                    //Toast.makeText(LoginActivity.this, postData.toString() + "Data sent",
                    //        Toast.LENGTH_LONG).show();


                    //SendDeviceDetails sa = new SendDeviceDetails();//("http://127.0.0.1:8000/login/", postData.toString())
                    new LoginActivity.SendDeviceDetails().execute(local, postData.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "login failed 2 ==)",
                            Toast.LENGTH_LONG).show();
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    private class SendDeviceDetails extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(final String... params) {


            Thread t = new Thread() {



                public void run() {
                    Looper.prepare(); //For Preparing Message Pool for the child Thread
                    String data = "";

                    HttpURLConnection httpURLConnection = null;



                    try {



                        httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                        httpURLConnection.setRequestMethod("POST");

                        httpURLConnection.setDoOutput(true);

                        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                        wr.writeBytes("PostData=" + params[1]);//----------------------- Check point --!
                        Toast.makeText(LoginActivity.this, params[1] + "Data sent",
                                Toast.LENGTH_LONG).show();

                        wr.flush();
                        wr.close();

                        /*
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);

                        int inputStreamData = inputStreamReader.read();
                        while (inputStreamData != -1) {
                            char current = (char) inputStreamData;
                            inputStreamData = inputStreamReader.read();
                            data += current;
                        }*/

                        InputStream in = httpURLConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        StringBuilder result = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null)
                        {
                            result.append(line);
                        }
                        System.out.println(result.toString());



                    /*Checking response */
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                            Toast.makeText(LoginActivity.this, "Connection disconnected",
                                    Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Cannot Estabilish Connection",
                                Toast.LENGTH_LONG).show();
                    }

                    Looper.loop(); //Loop in the message queue
                }
            };

            t.start();
            return data;

        }

    }

}