package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.damir.regattatracker.brod.BoatCallback;
import com.damir.regattatracker.brod.Brod;
import com.damir.regattatracker.brod.BrodAdapter;
import com.damir.regattatracker.brod.BrodController;

import org.json.JSONException;

import java.util.ArrayList;

public class OdaberiBrodActivity extends AppCompatActivity implements BoatCallback {
    EditText naziv;
    ArrayList<Brod> sviBrodovi;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_brod);
        naziv = findViewById(R.id.editText);
        sviBrodovi = new ArrayList<Brod>();
        BrodController.getBrodovi(this, this);
    }

    public void vratiNoviBrod(View view){
        Brod novi = new Brod(naziv.getText().toString());
        try {
            BrodController.postBrodovi(this, this, novi);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetBoatCallback(ArrayList<Brod> odg, String error) {
        if (error != null){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return;
        }
        sviBrodovi = odg;
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

    @Override
    public void onPostBoatCallback(Brod odg, String error) {
        Toast.makeText(this, "Uspjesno dodan novi brod: "+odg.getName(), Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        intent.putExtra("id", String.valueOf(odg.getId()));
        intent.putExtra("naziv", odg.getName());
        setResult(RESULT_OK, intent);
        finish();
    }
}
