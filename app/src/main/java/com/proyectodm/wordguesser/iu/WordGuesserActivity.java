package com.proyectodm.wordguesser.iu;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.core.Jugador;
import com.proyectodm.wordguesser.core.WordGuesserApplication;
import com.proyectodm.wordguesser.database.DBManager;

public class WordGuesserActivity extends AppCompatActivity {

    private final String JUGADOR_LOGUEADO = "loggedPlayer";
    private final String JUGADOR_LOGUEADO_NOMBRE = "loggedUsername";
    private final String JUGADOR_LOGUEADO_PASSWD = "loggedPasswd";

    protected DBManager getDbManager(){
        return ((WordGuesserApplication) getApplication()).getDbManager();
    }

    protected boolean isJugadorLogueado(){
        SharedPreferences prefs = this.getSharedPreferences(JUGADOR_LOGUEADO, Context.MODE_PRIVATE);
        String usr = prefs.getString(JUGADOR_LOGUEADO_NOMBRE,"");
        String pswd = prefs.getString(JUGADOR_LOGUEADO_PASSWD,"");

        Jugador jugador = getDbManager().checkLogin(usr,pswd);
        if(jugador != null){
            setJugadorLogueado(jugador);
        }
        return ((WordGuesserApplication) getApplication()).isJugadorLogueado();
    }

    protected Jugador getJugadorLogueado(){
        return ((WordGuesserApplication) getApplication()).getJugador();
    }

    protected void setJugadorLogueado(Jugador jugador){
        ((WordGuesserApplication) getApplication()).setJugador(jugador);
        SharedPreferences prefs = this.getSharedPreferences(JUGADOR_LOGUEADO, Context.MODE_PRIVATE);;
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(JUGADOR_LOGUEADO_NOMBRE, jugador.getUsuario());
        editor.putString(JUGADOR_LOGUEADO_PASSWD, jugador.getContraseña());
        editor.commit();
    }

    protected void clearJugadorLogueado(){
        ((WordGuesserApplication) getApplication()).clearJugador();
        SharedPreferences prefs = this.getSharedPreferences(JUGADOR_LOGUEADO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
