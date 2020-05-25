package com.damir.regattatracker.API;

public interface APICallback {

    void onGetCallback(Object response, String error);
    void onPostCallback(Object response, String error);
}
