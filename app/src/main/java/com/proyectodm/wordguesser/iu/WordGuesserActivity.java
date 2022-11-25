package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.core.Jugador;
import com.proyectodm.wordguesser.core.WordGuesserApplication;
import com.proyectodm.wordguesser.database.DBManager;

public class WordGuesserActivity extends AppCompatActivity {

    protected DBManager getDbManager(){
        return ((WordGuesserApplication) getApplication()).getDbManager();
    }

    protected boolean hayJugadorLogueado(){
        return ((WordGuesserApplication) getApplication()).isJugadorLogueado();
    }

    protected void setJugadorLogueado(Jugador jugador){
        ((WordGuesserApplication) getApplication()).setJugador(jugador);
        // AStodo guardar en preferencia getSharedPreferences apuntes
    }
}
