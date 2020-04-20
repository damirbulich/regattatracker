package com.damir.regattatracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class OdaberiBrodActivity extends AppCompatActivity {
    EditText naziv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odaberi_brod);
        naziv = findViewById(R.id.editText);
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
