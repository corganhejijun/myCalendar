package com.hfnu.corgan.mybasiccalendar;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonThread implements Runnable {
    private String urlStr;
    private Handler handler;
    Resources resources;
    public JsonThread(Resources resources, String url, Handler handler){
        urlStr = url;
        this.handler = handler;
        this.resources = resources;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if (code == resources.getInteger(R.integer.http_response_ok)){
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                BufferedReader bfReader = new BufferedReader(reader);
                StringBuilder strBuilder = new StringBuilder();
                String line;
                while ((line = bfReader.readLine()) != null){
                    strBuilder.append(line);
                }
                bfReader.close();
                reader.close();
                JSONObject obj = new JSONObject(strBuilder.toString());
                Message msg = new Message();
                msg.obj = obj;
                msg.what = R.integer.http_get_json_success;
                handler.sendMessage(msg);
            }
            else
            {
                throw new Exception(code + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
