package com.hfnu.corgan.mybasiccalendar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler extends Handler {
    MainActivity main;
    public JsonHandler(MainActivity main){
        this.main = main;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case R.integer.http_get_json_success:
                JSONObject obj = (JSONObject)msg.obj;
                handleJson(obj);
                break;
            default: break;

        }
    }

    void handleJson(JSONObject obj){
        try {
            Boolean result = obj.getBoolean("result");
            String event = obj.getString("event");
            if (result == false) {
                Log.v("info", "json result false, event is " + event + ", message is " + obj.getString("msg"));
                main.onFailed(event);
                return;
            }
            if (event.equals("login")){
                main.onLoginSuccess();
            }
            if (event.equals("register")){
                main.onLoginSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
