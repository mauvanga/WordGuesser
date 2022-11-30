package com.proyectodm.wordguesser.iu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    boolean hayFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        ListView lvHistorial = findViewById(R.id.lvHistorialPartidas);
        TextView textViewRachaActual = findViewById(R.id.textViewRachaActual);
        TextView textViewRachaMejor = findViewById(R.id.textViewMejorRacha);
        TextView textViewNumPartidas = findViewById(R.id.textViewPartidasJugadas);
        TextView textViewNumVictorias = findViewById(R.id.textViewVictorias);
        Button buttonLimpiar = (Button) findViewById(R.id.buttonLimpiar);

        textViewRachaActual.setText(String.valueOf(getJugadorLogueado().getRachaActual()));
        textViewRachaMejor.setText(String.valueOf(getJugadorLogueado().getMejorRacha()));
        filtroModo = MODO;
        filtroLenguaje = IDIOMA;
        filtroDificultad = DIFICULTAD;
        TextView filterGameMode = (TextView) this.findViewById(R.id.textViewFiltroModo);
        TextView filterGameLanguage = (TextView) this.findViewById(R.id.textViewFiltroIdioma);
        TextView filterGameDifficulty = (TextView) this.findViewById(R.id.textViewFiltroDificultad);

        this.registerForContextMenu(filterGameMode);
        this.registerForContextMenu(filterGameLanguage);
        this.registerForContextMenu(filterGameDifficulty);

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

        buttonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                filterGameDifficulty.setText(getString(R.string.difficulty));
                filterGameMode.setText(getString(R.string.mode));
                filterGameLanguage.setText(getString(R.string.language));
                Cursor cursor = getDbManager().findGames(getJugadorLogueado().getIdJugador());
                filtro.add(getDbManager().GAME_COLUMN_JUGADOR);
                filtro.add(getDbManager().GAME_COLUMN_RESULTADO);
                filtroValores.add(String.valueOf(getJugadorLogueado().getIdJugador()));
                filtroValores.add(String.valueOf(1));
                juegoCursorAdapter.changeCursor(cursor);

            }
        });

    }

    // todo hay que añadir filtros

    @Override
    protected void onResume() {
        super.onResume();
        actualizarFiltros();
        Cursor cursor = filtrar();
        juegoCursorAdapter.changeCursor(cursor);
    }

    protected void actualizarFiltros(){
        TextView viewMode = (TextView) this.findViewById(R.id.textViewFiltroModo);
        TextView viewLanguage = (TextView) this.findViewById(R.id.textViewFiltroIdioma);
        TextView viewDifficulty = (TextView) this.findViewById(R.id.textViewFiltroDificultad);

        viewMode.setText(getTranslatedText(filtroModo));
        viewLanguage.setText(getTranslatedText(filtroLenguaje));
        viewDifficulty.setText(getTranslatedText(filtroDificultad));
    }

    protected Cursor filtrar() {
        filtro.clear();
        filtroValores.clear();
        hayFiltro = false;
        Cursor cursor;

        if (!filtroModo.equalsIgnoreCase(MODO)) {
            filtro.add(getDbManager().GAME_COLUMN_MODO);
            filtroValores.add(filtroModo);
            hayFiltro = true;
        }
        if (!filtroLenguaje.equalsIgnoreCase(IDIOMA)) {
            filtro.add(getDbManager().GAME_COLUMN_IDIOMA);
            filtroValores.add(filtroLenguaje);
            hayFiltro = true;
        }
        if (!filtroDificultad.equalsIgnoreCase(DIFICULTAD)) {
            filtro.add(getDbManager().GAME_COLUMN_DIFICULTAD);
            filtroValores.add(filtroDificultad);
            hayFiltro = true;
        }
        if (hayFiltro) {
            filtro.add(getDbManager().GAME_COLUMN_JUGADOR);
            filtroValores.add(String.valueOf(getJugadorLogueado().getIdJugador()));
            cursor = getDbManager().findGamesResult(filtro, filtroValores);
        } else{
            cursor = getDbManager().findGames(getJugadorLogueado().getIdJugador());
        }
        return cursor;
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
        actualizarFiltros();
        juegoCursorAdapter.changeCursor(filtrar());
        return toret;
    }
}
