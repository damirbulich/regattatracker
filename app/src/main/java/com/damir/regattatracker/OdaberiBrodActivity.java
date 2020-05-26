package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.damir.regattatracker.brod.Brod;
import com.damir.regattatracker.brod.BrodAdapter;
import com.damir.regattatracker.API.APICallback;
import com.damir.regattatracker.API.APIHelper;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OdaberiBrodActivity extends AppCompatActivity implements APICallback {
    EditText naziv;
    ArrayList<Brod> sviBrodovi;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_brod);
        naziv = findViewById(R.id.editText);
        sviBrodovi = new ArrayList<Brod>();
        APIHelper.getRequest(this, this, "https://test.dbulic.com/api/brodovi", new TypeToken<ArrayList<Brod>>(){}.getType());
    }

    public void vratiNoviBrod(View view){
        String ime = naziv.getText().toString().trim();
        if(!ime.equals("")) {
            Brod novi = new Brod(ime);
            try {
                APIHelper.postRequest(this, this, "https://test.dbulic.com/api/brodovi", novi, new TypeToken<Brod>() {
                }.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "Unesite ime broda!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetCallback(Object response, String error, Type klasa) {
        if (error != null){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return;
        }

        if(klasa.toString().equals(new TypeToken<ArrayList<Brod>>(){}.getType().toString())) {
            sviBrodovi = (ArrayList<Brod>) response;
            listView = findViewById(R.id.lista);
            BrodAdapter adapter = new BrodAdapter(this, sviBrodovi);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = getIntent();
                    intent.putExtra("id", String.valueOf(sviBrodovi.get(position).getId()));
                    intent.putExtra("naziv", sviBrodovi.get(position).getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }



    @Override
    public void onPostCallback(Object response, String error, Type klasa) {
        if (klasa.toString().equals(new TypeToken<Brod>() {}.getType().toString())){
            Brod odg = (Brod) response;
            Toast.makeText(this, "Uspjesno dodan novi brod: " + odg.getName(), Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            intent.putExtra("id", String.valueOf(odg.getId()));
            intent.putExtra("naziv", odg.getName());
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
