package com.proyectodm.wordguesser.iu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Jugador;

public class ComoJugarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_como_jugar);
        Button buttonTrucos = (Button) findViewById(R.id.buttonTrucos);

        buttonTrucos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComoJugarActivity.this, TrucosActivity.class);
                startActivity(i);
            }
        });

    }



}
