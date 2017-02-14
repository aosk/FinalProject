package com.example.abdulrahmanalshaghdali.letsunite;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by abdulrahmanalshaghdali on 27/11/2016.
 */

public class ConnectionToPython
{
    public static HttpURLConnection connect (String urlAdress)
    {
        try {
            URL url = new URL(urlAdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);
            con.setDoOutput(true);
            
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
