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

                String server = "http://54.191.242.216:8000/";
//                String local = "http://10.0.2.2:8000";



                try {
                    JSONObject postData = new JSONObject();
                    postData.put("email", mEmailView.getText().toString());
                    postData.put("password", mPasswordView.getText().toString());

                    String str = postData.toString();
                    String local = "http://10.0.2.2:8000/login2/";

                    /*AsyncTask<String, Void, String> asyncLoad = new AsyncTask<String, Void, String>(){

                        @Override
                        protected String doInBackground (String... params){



                            try {

                                HttpURLConnection httpURLConnection = null;

                                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();


                                httpURLConnection.setDoOutput(true);
                                httpURLConnection.setDoInput(true);
                                httpURLConnection.setUseCaches(false);
                                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                                httpURLConnection.setRequestProperty("Accept", "application/json");
                                httpURLConnection.setRequestMethod("POST");
                                Log.d("before connection","hi");
                                httpURLConnection.connect();
                                Log.d("After","Horray");
                                OutputStream wr = httpURLConnection.getOutputStream();
                                OutputStreamWriter osw = new OutputStreamWriter(wr, "UTF-8");
                                osw.write(params[1]);//----------------------- Check point --!
                                Log.d("params[1]",params[1]);
                                osw.flush();
                                osw.close();
                                wr.close();


                                InputStream in = httpURLConnection.getInputStream();
                                InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
                                BufferedReader br = new BufferedReader(inputStreamReader);

                                String line = null;
                                String o = null;
                                while ((line = br.readLine()) != null) {
                                    o += line;
                                }
                                Log.d("MyApp", "I am here  <===================================== !!>");
                                System.out.println(o);
                                br.close();
                                return params[2];

                            } catch (Exception e) {
                                e.printStackTrace();
                                //Toast.makeText(LoginActivity.this, "Cannot Estabilish Connection",
                                 //       Toast.LENGTH_LONG).show();
                            }
                            return null;
                        }

                            @Override
                        protected void onPostExecute (String result){
                                super.onPostExecute(result);
                                Toast.makeText(LoginActivity.this, result,
                                               Toast.LENGTH_LONG).show();
                        }

                            //Looper.loop(); //Loop in the message queue
                            //}
                    };&*/

                    BackgroundTasks asyncLoad = new BackgroundTasks();
                    asyncLoad.execute(local,str);

                } catch(JSONException e){
                    e.printStackTrace();
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
