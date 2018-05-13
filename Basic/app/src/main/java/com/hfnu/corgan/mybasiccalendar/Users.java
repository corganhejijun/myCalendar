package com.hfnu.corgan.mybasiccalendar;

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

    public Users(Resources resources, SharedPreferences share, Handler handler) {
        password = username = "";
        this.share = share;
        this.handler = handler;
        this.resources = resources;
    }

    public Boolean login(String username, String password){
        this.username = username;
        this.password = encrypt(password);
        final String urlStr = resources.getString(R.string.server_url) + "?username=" + username + "&password=" + password;
        Thread thread = new Thread(new JsonThread(resources, urlStr, handler));
        thread.start();
        SharedPreferences.Editor editor = share.edit();
        editor.putString("username", username);
        editor.putString("password", encrypt(password));
        editor.apply();
        return true;
    }

    public Boolean register(String username, String password){
        return login(username, password);
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
        if (username.equals("abc") && password.equals(encrypt("123"))) {
            this.username = username;
            this.password = password;
            return true;
        }
        return false;
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
