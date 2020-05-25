package com.damir.regattatracker.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class APIHelper {

    /**
     * Dohvaca podatke get requestom sa APIja
     *
     * @param context Kontekst aktivnost koja poziva API
     * @param callerClass Klasa koja poziva API, mora implementirati APICallback sucelje
     * @param url Url APIja, endpoint s kojeg se dohvacaju podatci
     * @param tip Tip podatka koji se vraca
     **/
    public static void getRequest(Context context, final APICallback callerClass, String url, final Type tip){
        final RequestQueue[] queue = {Volley.newRequestQueue(context)};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            Gson g = new Gson();
                            Log.i("type: ", tip.toString());
                            callerClass.onGetCallback(g.fromJson(data.toString(), tip), null, tip);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            String errorString = error.toString();
                            callerClass.onGetCallback(null, errorString, null);
                        }
                    }
                });

        queue[0].add(request);
    }

    /**
     * Šalje podatke post requestom na API
     *
     * @param context Kontekst aktivnost koji poziva API
     * @param callerClass Klasa koja poziva API, mora implementirati APICallback sucelje
     * @param url Url APIja, endpoint s kojeg se dohvacaju podatci
     * @param data Podatci koji se salju post requestom, moralju implementirati PostData sucelje
     * @param tip Tip podatka koji se vraca
     */
    public static void postRequest(Context context, final APICallback callerClass, String url, PostData data, final Type tip) throws JSONException {
        final RequestQueue[] queue = {Volley.newRequestQueue(context)};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data.serialiseData()),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            Gson g = new Gson();
                            callerClass.onPostCallback(g.fromJson(data.toString(), tip), null, tip);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorString = error.networkResponse.toString();
                        callerClass.onPostCallback(null, errorString, null);
                    }
                });

        queue[0].add(request);
    }
}
