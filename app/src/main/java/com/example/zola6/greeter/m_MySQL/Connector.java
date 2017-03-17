package com.example.zola6.greeter.m_MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Zola6 on 2017. 02. 21..
 */

public class Connector {
    public static Object connection(String urlAddress)
    {
        try {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            //SET CON PROPERTIES
            con.setRequestMethod("GET");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);

            return con;

        } catch (ProtocolException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }
    }

    public static HttpURLConnection connect(String urlAddress)
    {
        try
        {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            //SET PROPERTIES
            con.setRequestMethod("POST");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);
            con.setDoOutput(true);
            //RETURN
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
