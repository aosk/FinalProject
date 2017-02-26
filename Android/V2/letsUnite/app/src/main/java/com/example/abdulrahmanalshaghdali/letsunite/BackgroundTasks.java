package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alshaghdali on 23/02/2017.
 */

public class BackgroundTasks extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground (String... params){



        try {

            String local = "http://10.0.2.2:8000/";
            String machine = "http://192.168.0.171:8000/";//to test phone on local
            String server = "http://54.191.242.216:8000/";


            HttpURLConnection httpURLConnection = null;

            httpURLConnection = (HttpURLConnection) new URL(local+""+params[0]).openConnection();


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

        Log.d("Finshed ", "Process");

    }

    //Looper.loop(); //Loop in the message queue
    //}


}
