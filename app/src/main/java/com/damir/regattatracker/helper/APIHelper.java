package com.damir.regattatracker.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIHelper {

    public static void getRequest(Context context, final APICallback callerClass, String url){
        final RequestQueue[] queue = {Volley.newRequestQueue(context)};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            callerClass.onGetCallback(data.toString(), null);
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
                            callerClass.onGetCallback(null, errorString);
                        }
                    }
                });

        queue[0].add(request);
    }

    public static void postRequest(Context context, final APICallback callerClass, String url, PostData data) throws JSONException {
        final RequestQueue[] queue = {Volley.newRequestQueue(context)};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data.serialiseData()),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            callerClass.onPostCallback(data.toString(), null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorString = error.networkResponse.toString();
                        callerClass.onPostCallback(null, errorString);
                    }
                });

        queue[0].add(request);
    }
}
