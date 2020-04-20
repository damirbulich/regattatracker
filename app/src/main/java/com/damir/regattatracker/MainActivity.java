package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {
    public static Brod mojBrod;
    TextView naziv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadBrod();
        naziv = findViewById(R.id.textView);
        if(mojBrod!=null){
            naziv.setText("Trenutni brod: "+mojBrod.getNaziv());
        }else{
            naziv.setText("Nije odabran brod!");
        }
    }
    public void start(View v){
        if(mojBrod == null){
            Intent i = new Intent(this, OdaberiBrodActivity.class);
            startActivityForResult(i, 1);
        } else {
            //Intent i = new Intent();
        }
    }

    public void change(View v){
        Intent i = new Intent(this, OdaberiBrodActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* Prezentacija Aktivnosti na Merlinu */
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int id = Integer.parseInt(data.getStringExtra("id"));
                String ime = data.getStringExtra("naziv");
                Log.i("id-naziv", id+" "+ime);
                mojBrod = new Brod(id, ime);
                if(mojBrod!=null){
                    naziv.setText("Trenutni brod: "+mojBrod.getNaziv());
                }else{
                    naziv.setText("Nije odabran brod!");
                }
                saveBrod();
            }
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
