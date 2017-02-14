package com.example.abdulrahmanalshaghdali.letsunite;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
                Toast.makeText(GetActivity.this, " posted to the server !! " ,
                        Toast.LENGTH_LONG).show();

                String url = "https://api.github.com/users";
                String url2 = "http://54.191.242.216:8080/";
                String url3 = "http://localhost:8000/users/";


                //new GetActivity.Httprequest().execute(url3);
            }
        });
    }

    /*
   *
   * Http API
   *
   * */
    public class Httprequest extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {

            HttpURLConnection connection = null ;
            BufferedReader reader = null ;

            try
            {
                URL url = new URL(params[0]);

                connection = (HttpURLConnection) url.openConnection();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while((line = reader.readLine()) != null)
                {

                    buffer.append(line);
                }

                return buffer.toString();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(connection != null )
                {

                    connection.disconnect();
                }

                if(reader != null)
                {
                    try
                    {
                        reader.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            tx.setText(s.toString());


        }
    }
}
