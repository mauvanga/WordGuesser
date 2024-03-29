package com.proyectodm.wordguesser.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.proyectodm.wordguesser.R;

public class SelectGameActivity extends WordGuesserActivity {

    String modo_partida = CLASICO;
    String lenguaje_partida = CASTELLANO;
    String dificultad_partida = NORMAL;
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
        TextView changueGameMode = (TextView) this.findViewById(R.id.textViewModeSelected);
        TextView changueGameLanguage = (TextView) this.findViewById(R.id.textViewIdiomaSelected);
        TextView changueGameDifficulty = (TextView) this.findViewById(R.id.textViewDificultadSelected);

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

        viewMode.setText(getTranslatedText(modo_partida));
        viewLanguage.setText(getTranslatedText(lenguaje_partida));
        viewDifficulty.setText(getTranslatedText(dificultad_partida));
    }

    public void onCreateContextMenu(ContextMenu contxt, View v, ContextMenu.ContextMenuInfo cmi) {
        if (v.getId() == R.id.textViewModeSelected) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_modo, contxt);
        }
        if (v.getId() == R.id.textViewIdiomaSelected) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_lenguaje, contxt);
        }
        if (v.getId() == R.id.textViewDificultadSelected) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_dificultad, contxt);
        }
    }

    @Override
    public boolean onContextItemSelected (MenuItem menuItem)
    {
        boolean toret = false;
        switch (menuItem.getItemId()) {
            case R.id.facil:
                this.dificultad_partida = FACIL;
                this.maximo_intentos=7;
                toret = true;
                break;
            case R.id.normal:
                this.dificultad_partida = NORMAL;
                this.maximo_intentos=5;
                toret = true;
                break;
            case R.id.dificil:
                this.dificultad_partida = DIFICIL;
                this.maximo_intentos=3;
                toret = true;
                break;
            case R.id.es:
                this.lenguaje_partida = CASTELLANO;
                toret = true;
                break;
            case R.id.en:
                this.lenguaje_partida = INGLES;
                toret = true;
                break;
            case R.id.gl:
                this.lenguaje_partida = GALLEGO;
                toret = true;
                break;
            case R.id.clasico:
                this.modo_partida = CLASICO;
                toret = true;
                break;
            case R.id.contrarreloj:
                this.modo_partida = CONTRARRELOJ;
                toret = true;
                break;
            case R.id.cientifico:
                this.modo_partida = CIENTIFICO;
                toret = true;
                break;
        }
        actualizarAjustesPartida();
        return toret;
    }



}
