package com.proyectodm.wordguesser.core;

import android.app.Application;

import com.proyectodm.wordguesser.database.DBManager;

public class WordGuesserApplication extends Application {

    private DBManager dbManager;
    private Jugador jugador;

    @Override
    public void onCreate() {
        super.onCreate();
        this.dbManager = new DBManager(this);
        //ASDtodo acceder a las preferencias y ver si existe un jugador logueado
        //recuperar jugador de la db y establecerlo con setJugadorLogueado
    }

    public DBManager getDbManager(){
        return dbManager;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public boolean isJugadorLogueado(){
        return jugador != null;
    }

    public void clearJugador(){
        jugador = null;
        //ASDtodo borrar jugador de las preferencias
    }
}
