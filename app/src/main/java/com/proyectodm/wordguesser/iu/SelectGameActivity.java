package com.proyectodm.wordguesser.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;

public class SelectGameActivity extends WordGuesserActivity {

    String modo_partida = "clasico";
    String lenguaje_partida = "es";
    String dificultad_partida = "normal";
    int maximo_intentos=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_tipo_juego);
        Button buttonIniciarPartida = (Button) findViewById(R.id.buttonIniciarPartida);

        //boton que permite al usuario empezar una nueva partida
        buttonIniciarPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectGameActivity.this, GameActivity.class);
                i.putExtra("dificultad_partida", dificultad_partida);
                i.putExtra("modo_partida", modo_partida);
                i.putExtra("lenguaje_partida", lenguaje_partida);
                i.putExtra("maximo_intentos", maximo_intentos);
                startActivity(i);
            }
        });
        TextView changueGameMode = (TextView) this.findViewById(R.id.textViewGameMode);
        TextView changueGameLanguage = (TextView) this.findViewById(R.id.textViewGameLanguage);
        TextView changueGameDifficulty = (TextView) this.findViewById(R.id.textViewGameDifficulty);

        this.registerForContextMenu(changueGameMode);
        this.registerForContextMenu(changueGameLanguage);
        this.registerForContextMenu(changueGameDifficulty);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarAjustesPartida();
    }

    protected void actualizarAjustesPartida(){
        TextView viewMode = (TextView) this.findViewById(R.id.textViewModeSelected);
        TextView viewLanguage = (TextView) this.findViewById(R.id.textViewIdiomaSelected);
        TextView viewDifficulty = (TextView) this.findViewById(R.id.textViewDificultadSelected);

        viewMode.setText(modo_partida);
        viewLanguage.setText(lenguaje_partida);
        viewDifficulty.setText(dificultad_partida);
    }

    public void onCreateContextMenu(ContextMenu contxt, View v, ContextMenu.ContextMenuInfo cmi) {
        if (v.getId() == R.id.textViewGameMode) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_modo, contxt);
        }
        if (v.getId() == R.id.textViewGameLanguage) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_lenguaje, contxt);
        }
        if (v.getId() == R.id.textViewGameDifficulty) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_dificultad, contxt);
        }
    }

    @Override
    public boolean onContextItemSelected (MenuItem menuItem)
    {
        boolean toret = false;
        switch (menuItem.getItemId()) {
            case R.id.facil:
                this.dificultad_partida = "facil";
                this.maximo_intentos=7;
                toret = true;
                break;
            case R.id.normal:
                this.dificultad_partida = "normal";
                this.maximo_intentos=5;
                toret = true;
                break;
            case R.id.dificil:
                this.dificultad_partida = "dificil";
                this.maximo_intentos=3;
                toret = true;
                break;
            case R.id.es:
                this.lenguaje_partida = "es";
                toret = true;
                break;
            case R.id.en:
                this.lenguaje_partida = "en";
                toret = true;
                break;
            case R.id.gl:
                this.lenguaje_partida = "gl";
                toret = true;
                break;
            case R.id.clasico:
                this.modo_partida = "clasico";
                toret = true;
                break;
            case R.id.contrarreloj:
                this.modo_partida = "contrarreloj";
                toret = true;
                break;
            case R.id.cientifico:
                this.modo_partida = "cientifico";
                toret = true;
                break;
        }
        actualizarAjustesPartida();
        return toret;
    }



}
