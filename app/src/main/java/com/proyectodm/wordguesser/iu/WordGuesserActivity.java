package com.proyectodm.wordguesser.iu;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Jugador;
import com.proyectodm.wordguesser.core.WordGuesserApplication;
import com.proyectodm.wordguesser.database.DBManager;

public class WordGuesserActivity extends AppCompatActivity {

    private final String JUGADOR_LOGUEADO = "loggedPlayer";
    private final String JUGADOR_LOGUEADO_NOMBRE = "loggedUsername";
    private final String JUGADOR_LOGUEADO_PASSWD = "loggedPasswd";

    protected final String INGLES = "en";
    protected final String CASTELLANO = "es";
    protected final String GALLEGO = "gl";

    protected final String NORMAL = "normal";
    protected final String FACIL = "facil";
    protected final String DIFICIL = "dificil";

    protected final String CLASICO = "clasico";
    protected final String CIENTIFICO = "cientifico";
    protected final String CONTRARRELOJ = "contrarreloj";

    protected final String MODO = "modo";
    protected final String DIFICULTAD = "dificultad";
    protected final String IDIOMA = "idioma";

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
        SharedPreferences prefs = this.getSharedPreferences(JUGADOR_LOGUEADO, Context.MODE_PRIVATE);
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

    public String getTranslatedText(String txt){
        String toret = "";
        if (txt.equalsIgnoreCase(CASTELLANO)){
            toret = getString(R.string.es);
        }
        if (txt.equalsIgnoreCase(INGLES)){
            toret = getString(R.string.en);
        }
        if (txt.equalsIgnoreCase(GALLEGO)){
            toret = getString(R.string.gl);
        }
        if (txt.equalsIgnoreCase(NORMAL)){
            toret = getString(R.string.normal);
        }
        if (txt.equalsIgnoreCase(FACIL)){
            toret = getString(R.string.facil);
        }
        if (txt.equalsIgnoreCase(DIFICIL)){
            toret = getString(R.string.dificil);
        }
        if (txt.equalsIgnoreCase(CLASICO)){
            toret = getString(R.string.clasico);
        }
        if (txt.equalsIgnoreCase(CIENTIFICO)){
            toret = getString(R.string.cientifico);
        }
        if (txt.equalsIgnoreCase(CONTRARRELOJ)){
            toret = getString(R.string.contrarreloj);
        }
        if (txt.equalsIgnoreCase(MODO)){
            toret = getString(R.string.mode);
        }
        if (txt.equalsIgnoreCase(DIFICULTAD)){
            toret = getString(R.string.difficulty);
        }
        if (txt.equalsIgnoreCase(IDIOMA)){
            toret = getString(R.string.select_idioma);
        }
        return toret;
    }
}
