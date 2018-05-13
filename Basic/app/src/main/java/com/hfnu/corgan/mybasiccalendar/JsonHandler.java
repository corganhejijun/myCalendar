package com.hfnu.corgan.mybasiccalendar;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case R.integer.http_get_json_success:
                try {
                    JSONObject obj = (JSONObject)msg.obj;
                    Log.v("info", "name:" + obj.getString("name") + " data:" + obj.getString("data"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default: break;

        }
    }
}
