package com.example.zola6.greeter.m_MySQL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by Zola6 on 2017. 03. 16..
 */

public class DataPackager {
    String query;
    //String name, position;
    public DataPackager(String query) {

        this.query = query;
        //this.name = name;
        //this.position = position;
    }
    public String packageData()
    {
        JSONObject jo=new JSONObject();
        StringBuffer queryString=new StringBuffer();
        try
        {
            //jo.put("Query",query);
            jo.put("Query",query);
            //jo.put("Position",position);
            Boolean firstValue=true;
            Iterator it=jo.keys();
            do {
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(firstValue)
                {
                    firstValue=false;
                }else {
                    queryString.append("&");
                }
                queryString.append(URLEncoder.encode(key,"UTF-8"));
                queryString.append("=");
                queryString.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return queryString.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
