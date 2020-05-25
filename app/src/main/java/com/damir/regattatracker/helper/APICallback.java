package com.damir.regattatracker.helper;

public interface APICallback {

    void onGetCallback(Object response, String error);
    void onPostCallback(Object response, String error);
}
