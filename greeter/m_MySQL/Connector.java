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

    String address = "";

    public Connector(String address){
        this.address = address;
    }

    public HttpURLConnection runQuery(String query) throws IOException {
            URL url=new URL(this.address);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            //SET CON PROPERTIEs
            con.setRequestMethod("POST");
            con.setRequestProperty("query", query);
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);
            return con;
    }
}
