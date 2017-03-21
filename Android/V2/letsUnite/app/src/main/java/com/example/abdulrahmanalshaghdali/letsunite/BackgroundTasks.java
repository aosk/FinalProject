package com.example.abdulrahmanalshaghdali.letsunite;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
/**
 * Created by alshaghdali on 23/02/2017.
 */

public class BackgroundTasks extends AsyncTask<String, String, String> {

    protected String r="Opssss!!";
    protected ArrayList<String> LABELS = null;
    protected ArrayList<String> config= null;

    private String fname = "mydata";
    //File f;

    @Override
    protected void onPreExecute(){
        LABELS = new ArrayList<>();
        config = new ArrayList<>();
        LABELS.add("Content-Type");
        LABELS.add("Accept");
        LABELS.add("UTF-8");
        //f.open("info");



    }
    @Override
    protected String doInBackground (String... params){

        if(params[2].equals("POST")){
            config.add("application/json");
            config.add("POST");
        }else if(params[2].equals("GET")) {
            config.add("application/html");
            config.add("GET");
        }else if(params[2].equals("PUT")){
            config.add("application/html");
            config.add("PUT");
        }else{
            //default config
            Log.d("Method","Not Known");
        }

        try {

            String local = "http://10.0.2.2:8000/";
            String machine = "http://192.168.0.171:8000/";//to test phone on local
            String server = "http://54.191.242.216:8000/";


            HttpURLConnection httpURLConnection = null;

            httpURLConnection = (HttpURLConnection) new URL(machine+""+params[0]).openConnection();

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty(LABELS.get(0), config.get(0));
            httpURLConnection.setRequestProperty(LABELS.get(1), config.get(0));
            if(params[2].equals("PUT")){
                //String group_id = f.readLine();
                //String user_id = f.readLine();
                //f.close();




                httpURLConnection.setRequestProperty("group_id", params[3]);
                httpURLConnection.setRequestProperty("user_id", params[1]);

                Log.d("Abdul ..!! ", "I am here  <=====================================DeBugLine=============================================>!!>");
                Log.d("This is parms[0] sent to server ", params[0]);
                Log.d("This is parms[1] sent to server ", params[1]);
                Log.d("This is parms[2] sent to server ", params[2]);
                Log.d("This is parms[3] sent to server ", params[3]);
                Log.d("This is parms[4] sent to server ", params[4]);
                Log.d("This is parms[5] sent to server ", params[5]);
                //Log.d("This is parms[3] sent to server ", params[3]);

                httpURLConnection.setRequestProperty("x_loc", params[4]);
                httpURLConnection.setRequestProperty("y_loc", params[5]);

            }
            httpURLConnection.setRequestMethod(config.get(1));
            //Log.d("Not connected yet Abdul !! "," -=-");
            httpURLConnection.connect();
            //Log.d("It is connected now !! "," okay");
            OutputStream wr = httpURLConnection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(wr, "UTF-8");
            osw.write(params[1]);//----------------------- Check point --!
            Log.d("config[1]============>",config.get(1));
            osw.flush();
            osw.close();
            wr.close();

            String respond=null;
            String respondCode = Integer.toString(httpURLConnection.getResponseCode());
            if(respondCode.equals("200")){
            InputStream in = httpURLConnection.getInputStream();
            Log.d("Connection   -----> ","closed");
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            if("PUT".equals(config.get(1))) {
                Log.d("Get IO","Before");
                BufferedReader br = new BufferedReader(inputStreamReader);
                Log.d("Got IO","Yeahhh");
                //Convert to JSON
                String line = null;
                while ((line = br.readLine()) != null) {
                    respond += (line+'\n');
                }
                br.close();
            }}else{
                respond=respondCode;
            }
            Log.d("Stream connected","-=-=-=-=-=-= Good Worked =-=-=-=-=-=-");

            //Log.d("Data line:",respond);
            Log.d("pick the nick !!  <===================================== !!>",respond);
            httpURLConnection.disconnect();
            return respond;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }


    @Override
    protected void onPostExecute (String result){
        Log.d("Finshed ", "Execution");
    }

}