package com.damir.regattatracker.brod;

import android.content.Context;

import com.damir.regattatracker.helper.APIHelper;
import com.damir.regattatracker.helper.APICallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BrodController implements APICallback {

    private BoatCallback myCallback;

    public BrodController(BoatCallback _boatCallback){
        myCallback = _boatCallback;
    }

    public void getBrodovi(Context context){
        APIHelper.getRequest(context, this, "https://test.dbulic.com/api/boats");
    }

    @Override
    public void onCallback(String response, String error) {
        Gson g = new Gson();
        Type tip = new TypeToken<ArrayList<Brod>>() {}.getType();
        ArrayList<Brod> odg = g.fromJson(response, tip);
        myCallback.onBoatCallback(odg, null);
    }
}
