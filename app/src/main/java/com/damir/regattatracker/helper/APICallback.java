package com.damir.regattatracker.helper;

public interface APICallback {

    void onGetCallback(String response, String error);
    void onPostCallback(String response, String error);
}
