package com.damir.regattatracker.helper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIHelper {
    private static APICallback myCallback;

    public static void getRequest(Context context, APICallback callerClass, String url){
        myCallback = callerClass;
        final RequestQueue[] queue = {Volley.newRequestQueue(context)};

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray data = response.getJSONArray("data");
                            myCallback.onCallback(data.toString(), null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myCallback = null;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorString = error.networkResponse.toString();
                        myCallback.onCallback(null, errorString);
                    }
                });

        queue[0].add(request);
    }
}
