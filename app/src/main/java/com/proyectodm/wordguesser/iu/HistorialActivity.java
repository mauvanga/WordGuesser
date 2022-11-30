package com.proyectodm.wordguesser.iu;

import android.database.Cursor;
import android.os.Bundle;
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        getDbManager().close();
        juegoCursorAdapter.getCursor().close();
    }
}
