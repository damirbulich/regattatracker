package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.damir.regattatracker.brod.BoatCallback;
import com.damir.regattatracker.brod.Brod;
import com.damir.regattatracker.brod.BrodAdapter;
import com.damir.regattatracker.brod.BrodController;
import java.util.ArrayList;
import java.util.List;

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
        Intent intent = getIntent();
        //TODO napraviti dohvat s apija i spremiti u listu brodova, ako se kreira novi brod poslati na api i vratiti isti
        intent.putExtra("naziv", naziv.getText().toString());
        intent.putExtra("id", "123");
        setResult(RESULT_OK, intent);
        finish();
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
    }

    @Override
    public void onPostBoatCallback() {

    }
}
