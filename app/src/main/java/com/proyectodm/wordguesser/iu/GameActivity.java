package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_seleccionar_modo); // Cargar la del modo correspondiente
    }
}
