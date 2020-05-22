package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OdaberiBrodActivity extends AppCompatActivity {
    EditText naziv;
    List<Brod> sviBrodovi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_brod);
        naziv = findViewById(R.id.editText);
        sviBrodovi = new ArrayList<Brod>();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Gson g = new Gson();
                            JSONArray data = response.getJSONArray("data");
                            for(int i = 0; i < data.length(); i++){
                                Type tip = new TypeToken<Brod>() {}.getType();
                                Brod br = g.fromJson(data.getJSONObject(i).toString(), tip);
                                sviBrodovi.add(br);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
        });

        queue.add(request);

    }

    public void vratiNoviBrod(View view){
        Intent intent = getIntent();
        //TODO napraviti dohvat s apija i spremiti u listu brodova, ako se kreira novi brod poslati na api i vratiti isti
        intent.putExtra("naziv", naziv.getText().toString());
        intent.putExtra("id", "123");
        setResult(RESULT_OK, intent);
        finish();
    }
}
