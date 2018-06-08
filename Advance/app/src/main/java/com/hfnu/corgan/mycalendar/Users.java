package com.hfnu.corgan.mycalendar;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Users {
    String username;
    String password;
    SharedPreferences share;
    Resources resources;
    Handler handler;
    JsonThread thread;

    public Users(Resources resources, SharedPreferences share, Handler handler, JsonThread thread) {
        password = username = "";
        this.share = share;
        this.handler = handler;
        this.resources = resources;
        this.thread = thread;
    }

    public Boolean login(String username, String password) {
        return login(username, encrypt(password), false);
    }

    public String getUsername(){
        return username;
    }

    private Boolean login(String username, String password, Boolean register){
        this.username = username;
        this.password = password;
        String urlStr = resources.getString(R.string.server_url) + "?username=" + username + "&password=" + this.password;
        if (register){
            urlStr += "&register";
        }
        thread.pushUrlStr(urlStr);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("username", username);
        editor.putString("password", this.password);
        editor.apply();
        return true;
    }

    public Boolean register(String username, String password){
        return login(username, encrypt(password), true);
    }

    public void logout(){
        password = username = "";
        SharedPreferences.Editor editor = share.edit();
        editor.putString("username", "");
        editor.putString("password", "");
        editor.apply();
    }

    public Boolean checkLogin(String username, String password){
        if (username.equals("") || password.equals(""))
            return false;
        login(username, password, false);
        return true;
    }

    private String encrypt(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte data[] = md.digest(password.getBytes());
            StringBuilder strBuild =new StringBuilder();
            for(byte b:data){
                strBuild.append(Integer.toHexString(b&0xff));
            }
            return strBuild.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
