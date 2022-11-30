package com.proyectodm.wordguesser.iu;

import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.JuegoCursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class HistorialActivity extends WordGuesserActivity {

    private JuegoCursorAdapter juegoCursorAdapter;
    List<String> filtro;
    List<String> filtroValores;
    String filtroModo;
    String filtroLenguaje;
    String filtroDificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        ListView lvHistorial = findViewById(R.id.lvHistorialPartidas);
        TextView textViewRachaActual = findViewById(R.id.textViewRachaActual);
        TextView textViewRachaMejor = findViewById(R.id.textViewMejorRacha);
        TextView textViewNumPartidas = findViewById(R.id.textViewPartidasJugadas);
        TextView textViewNumVictorias = findViewById(R.id.textViewVictorias);

        textViewRachaActual.setText(String.valueOf(getJugadorLogueado().getRachaActual()));
        textViewRachaMejor.setText(String.valueOf(getJugadorLogueado().getMejorRacha()));
        filtroModo = "<" + getTranslatedText(MODO) + ">";
        filtroLenguaje = "<" + getTranslatedText(IDIOMA) + ">";
        filtroDificultad = "<" + getTranslatedText(DIFICULTAD) + ">";

        juegoCursorAdapter = new JuegoCursorAdapter(HistorialActivity.this, null, getDbManager());
        lvHistorial.setAdapter(juegoCursorAdapter);

        filtro = new ArrayList<>();
        filtroValores = new ArrayList<>();
        Cursor cursor = getDbManager().findGames(getJugadorLogueado().getIdJugador());
        int partidas = cursor.getCount();
        if(partidas > 0){
            textViewNumPartidas.setText(String.valueOf(partidas));
            filtro.add(getDbManager().GAME_COLUMN_JUGADOR);
            filtro.add(getDbManager().GAME_COLUMN_RESULTADO);
            filtroValores.add(String.valueOf(getJugadorLogueado().getIdJugador()));
            filtroValores.add(String.valueOf(1));
            double victorias = getDbManager().findGamesResult(filtro, filtroValores).getCount();
            textViewNumVictorias.setText(Math.round((victorias/partidas)*100) + " %");
        } else {
            textViewNumPartidas.setText("0");
            textViewNumVictorias.setText("0 %");
        }

        juegoCursorAdapter.changeCursor(cursor);
        // todo hay que traducir los datos del listview
        //  es decir cambiar "gl" por "GALLEGO", "GALICIAN" o "GALEGO"
    }

    // todo hay que añadir filtros

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = getDbManager().findGames(getJugadorLogueado().getIdJugador());
        juegoCursorAdapter.changeCursor(cursor);
        actualizarAjustesPartida();
    }

    protected void actualizarAjustesPartida(){
        TextView viewMode = (TextView) this.findViewById(R.id.textViewFiltroModo);
        TextView viewLanguage = (TextView) this.findViewById(R.id.textViewFiltroIdioma);
        TextView viewDifficulty = (TextView) this.findViewById(R.id.textViewFiltroDificultad);

        viewMode.setText(getTranslatedText(filtroModo));
        viewLanguage.setText(getTranslatedText(filtroLenguaje));
        viewDifficulty.setText(getTranslatedText(filtroDificultad));
    }

    @Override
    protected void onPause() {
        super.onPause();
        getDbManager().close();
        juegoCursorAdapter.getCursor().close();
    }

    public void onCreateContextMenu(ContextMenu contxt, View v, ContextMenu.ContextMenuInfo cmi) {
        if (v.getId() == R.id.textViewFiltroModo) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_modo, contxt);
        }
        if (v.getId() == R.id.textViewFiltroIdioma) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_lenguaje, contxt);
        }
        if (v.getId() == R.id.textViewFiltroDificultad) {
            this.getMenuInflater().inflate(R.menu.menu_contextual_dificultad, contxt);
        }
    }

    @Override
    public boolean onContextItemSelected (MenuItem menuItem)
    {
        boolean toret = false;
        switch (menuItem.getItemId()) {
            case R.id.facil:
                this.filtroDificultad = FACIL;
                toret = true;
                break;
            case R.id.normal:
                this.filtroDificultad = NORMAL;
                toret = true;
                break;
            case R.id.dificil:
                this.filtroDificultad = DIFICIL;
                toret = true;
                break;
            case R.id.es:
                this.filtroLenguaje = CASTELLANO;
                toret = true;
                break;
            case R.id.en:
                this.filtroLenguaje = INGLES;
                toret = true;
                break;
            case R.id.gl:
                this.filtroLenguaje = GALLEGO;
                toret = true;
                break;
            case R.id.clasico:
                this.filtroModo = CLASICO;
                toret = true;
                break;
            case R.id.contrarreloj:
                this.filtroModo = CONTRARRELOJ;
                toret = true;
                break;
            case R.id.cientifico:
                this.filtroModo = CIENTIFICO;
                toret = true;
                break;
        }
        actualizarAjustesPartida();
        return toret;
    }
}
