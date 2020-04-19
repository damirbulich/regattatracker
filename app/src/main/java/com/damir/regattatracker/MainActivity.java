package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    public static Brod mojBrod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadBrod();
        TextView naziv = findViewById(R.id.textView);
        if(mojBrod!=null){
            naziv.setText("Trenutni brod: "+mojBrod.getNaziv());
        }else{
            naziv.setText("Nije odabran brod!");
        }
    }

    public void loadBrod(){
        SharedPreferences sprema = getSharedPreferences("moja_sprema", Context.MODE_PRIVATE);
        Gson g = new Gson();
        String json = sprema.getString("brod", null);
        Type tip = new TypeToken<Brod>() {}.getType();
        mojBrod = g.fromJson(json, tip);
    }

    public void saveBrod(){
        SharedPreferences sprema = getSharedPreferences("moja_sprema", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sprema.edit();
        Gson g = new Gson();
        String jsonBrod = g.toJson(mojBrod);
        editor.putString("brod",jsonBrod);
        editor.apply();
    }
}
