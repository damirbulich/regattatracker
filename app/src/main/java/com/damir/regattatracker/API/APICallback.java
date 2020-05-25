package com.damir.regattatracker.API;

import java.lang.reflect.Type;

public interface APICallback {

    void onGetCallback(Object response, String error, Type klasa);
    void onPostCallback(Object response, String error, Type klasa);
}
