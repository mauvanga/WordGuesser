package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.database.DBManager;


public class HistorialActivity extends WordGuesserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial); // Cargar la del modo correspondiente

        this.gestorDB = new DBManager( this.getApplicationContext() );
    }

    @Override
    public void onStart() {
        super.onStart();

        final ListView lvHistorial = this.findViewById(R.id.lvHistorialPartidas);

        this.adaptadorDB = new SimpleCursorAdapter(
                this,
                R.layout.activity_historial,
                null,
                new String[] {DBManager.GAME_COLUMN_PALABRA, DBManager.GAME_COLUMN_IDIOMA,DBManager.GAME_COLUMN_MODO, DBManager.GAME_COLUMN_DIFICULTAD},
                new int[] {R.id.textViewPalabra,  R.id.textViewIdioma, R.id.textViewModo,  R.id.textViewDificultad},
                0
        );
        lvHistorial.setAdapter(this.adaptadorDB);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        this.gestorDB.close();
        this.adaptadorDB.getCursor().close();
    }

    private DBManager gestorDB;
    private SimpleCursorAdapter adaptadorDB;
}
