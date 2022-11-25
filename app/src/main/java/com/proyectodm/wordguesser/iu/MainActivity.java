package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.database.DBManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean logueado = false; //Calcular
        if(logueado){
            this.startActivity( new Intent(this, MenuActivity.class));
        } else {
            this.startActivity( new Intent(this, LoginActivity.class));
        }
    }
}