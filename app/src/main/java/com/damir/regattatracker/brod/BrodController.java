package com.damir.regattatracker.brod;

import android.content.Context;

import com.damir.regattatracker.helper.APIHelper;
import com.damir.regattatracker.helper.APICallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BrodController implements APICallback {

    private static BoatCallback myCallback;
    private static BrodController instance;

    public static void setInstance(){
        if(instance == null)
            instance = new BrodController();
    }

    public static void getBrodovi(Context context, BoatCallback _boatCallback){
        setInstance();
        myCallback = _boatCallback;
        APIHelper.getRequest(context, instance, "https://test.dbulic.com/api/brodovi");
    }

    public static void postBrodovi(Context context, BoatCallback _boatCallback, Brod novi) throws JSONException {
        setInstance();
        myCallback = _boatCallback;
        APIHelper.postRequest(context, instance, "https://test.dbulic.com/api/brodovi", novi);
    }

    @Override
    public void onGetCallback(String response, String error) {
        if(error!=null) {
            myCallback.onGetBoatCallback(null, error);
            myCallback = null;
            return;
        }
        Gson g = new Gson();
        Type tip = new TypeToken<ArrayList<Brod>>() {}.getType();
        ArrayList<Brod> odg = g.fromJson(response, tip);
        myCallback.onGetBoatCallback(odg, null);
        myCallback = null;
    }

    @Override
    public void onPostCallback(String response, String error) {
        if(error!=null) {
            myCallback.onPostBoatCallback(null, error);
            myCallback = null;
            return;
        }
        Gson g = new Gson();
        Type tip = new TypeToken<Brod>() {}.getType();
        Brod odg = g.fromJson(response, tip);
        myCallback.onPostBoatCallback(odg, null);
        myCallback = null;
    }
}
