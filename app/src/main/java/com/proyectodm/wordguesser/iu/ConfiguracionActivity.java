package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;


public class ConfiguracionActivity extends WordGuesserActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion); // Cargar la del modo correspondiente
    }
}
