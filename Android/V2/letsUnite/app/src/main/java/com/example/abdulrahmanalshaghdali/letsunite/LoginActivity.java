package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity
{



    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button singinBTN ;
    private Button signupBTN ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        // password
        mPasswordView = (EditText) findViewById(R.id.password);
        singinBTN = (Button) findViewById(R.id.email_sign_in_button);
        singinBTN.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {

                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", mEmailView.getText().toString());
                    postData.put("password", mPasswordView.getText().toString());

                    Toast.makeText(LoginActivity.this, postData.toString(),
                            Toast.LENGTH_LONG).show();


                    SendDeviceDetails sa = new SendDeviceDetails();//("http://127.0.0.1:8000/login/", postData.toString())
                    sa.execute("http://54.191.242.216:8080/", postData.toString());
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
    }

    private class SendDeviceDetails extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data = "";

            HttpURLConnection httpURLConnection = null;
            try {

                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                wr.writeBytes("PostData=" + params[1]);
                wr.flush();
                wr.close();

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                }
            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(LoginActivity.this, "login failed: ",
                        Toast.LENGTH_LONG).show();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Log.e("TAG", result); // this is expecting a response code to be sent from your server upon receiving the POST data
        }
    }

}