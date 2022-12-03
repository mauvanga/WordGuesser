package com.proyectodm.wordguesser.core;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.database.DBManager;
import com.proyectodm.wordguesser.iu.WordGuesserActivity;

/**
 * Clase para dar apoyo al historial
 */
public class JuegoCursorAdapter extends CursorAdapter {

    private DBManager dbManager;

    public JuegoCursorAdapter(Context context, Cursor c, DBManager dbManager) {
        super(context, c, false);
        this.dbManager = dbManager;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_historial_items,
                parent, false);
    }

    class ViewHolder{
        LinearLayout linearLayout;
        TextView textViewPalabra;
        TextView textViewIdioma;
        TextView textViewModo;
        TextView textViewDificultad;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = null;
        if (view!=null) {
            viewHolder = (ViewHolder) view.getTag();
            if (viewHolder == null){
                viewHolder = new ViewHolder();
                viewHolder.linearLayout = view.findViewById(R.id.linearLayoutJuego);
                viewHolder.textViewPalabra = view.findViewById(R.id.textViewPalabra);
                viewHolder.textViewIdioma = view.findViewById(R.id.textViewIdioma);
                viewHolder.textViewModo = view.findViewById(R.id.textViewModo);
                viewHolder.textViewDificultad = view.findViewById(R.id.textViewDificultad);
                view.setTag(viewHolder);
            }
        }

        final Juego juego = dbManager.readJuego(cursor);
        viewHolder.textViewPalabra.setText(juego.getPalabra().toUpperCase());
        viewHolder.textViewIdioma.setText(juego.getIdioma().toUpperCase());
        String modo = ((WordGuesserActivity) context).getTranslatedText(juego.getModo());
        viewHolder.textViewModo.setText(modo);
        String dificultad = ((WordGuesserActivity) context).getTranslatedText(juego.getDificultad());
        viewHolder.textViewDificultad.setText(dificultad);
        if (juego.getResultado()){
            viewHolder.linearLayout.setBackgroundColor(Color.GREEN); //Fila color verde = Victoria
        }else{
            viewHolder.linearLayout.setBackgroundColor(Color.RED); //Fila color roja = Derrota
        }
    }
}
