package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Juego;

import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_clasico_normal); // Cargar la del modo correspondiente

        EditText palabraUsuarioClasicoNormal = (EditText) findViewById(R.id.palabraUsuarioClasicoNormal);

        int random = (int) (Math.random() * 11422 + 1);

        List<String> builder = new ArrayList<String>();

        try {
            String line;
            InputStream in = this.getAssets().open("en_5.txt");
            BufferedReader inf = new BufferedReader(new InputStreamReader(in));
            while ((line = inf.readLine()) != null) {
                builder.add(line);
            }
        } catch (IOException e) {
            builder.add("error");
        }

        String[] palabrasIngles = builder.toArray(new String[0]);

        String palabraJuego = palabrasIngles[random];


        Button buttonAceptarJuegoClasico = (Button) findViewById(R.id.buttonAceptarJuegoClasico);

        int intentos = 0;
        boolean partidaGanada = false;
        Juego juego = new Juego(intentos, partidaGanada);

        if(juego.getIntentos()==5){
            AlertDialog.Builder perdido = new AlertDialog.Builder(GameActivity.this);
            perdido.setMessage("¡Has perdido! La palabra era: "+palabraJuego).setCancelable(false)
                    .setPositiveButton("Volver a jugar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //volvemos a la actividad de login
                            Intent i = new Intent(GameActivity.this, GameActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("EXIT", true);
                            startActivity(i);
                            finish();

                        }
                    })
                    .setNegativeButton("Aceptar mi derrota", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = perdido.create();
            alert.show();
        }

        buttonAceptarJuegoClasico.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String palabra = palabraUsuarioClasicoNormal.getText().toString();

                if (!(palabra.length() == 5)) {

                } else {

                    switch (juego.getIntentos()) {
                        case 0:
                            LinearLayout l = (LinearLayout) findViewById(R.id.filaClasicoNormal1);
                            escribirFila(l, palabra);
                            comprobacionCorrecta(l, juego, palabra, palabraJuego);
                            break;

                        case 1:
                            LinearLayout l2 = (LinearLayout) findViewById(R.id.filaClasicoNormal2);
                            escribirFila(l2, palabra);
                            comprobacionCorrecta(l2, juego, palabra, palabraJuego);
                            break;

                        case 2:
                            LinearLayout l3 = (LinearLayout) findViewById(R.id.filaClasicoNormal3);
                            escribirFila(l3, palabra);
                            comprobacionCorrecta(l3, juego, palabra, palabraJuego);
                            break;

                        case 3:
                            LinearLayout l4 = (LinearLayout) findViewById(R.id.filaClasicoNormal4);
                            escribirFila(l4, palabra);
                            comprobacionCorrecta(l4, juego, palabra, palabraJuego);
                            break;

                        case 4:
                            LinearLayout l5 = (LinearLayout) findViewById(R.id.filaClasicoNormal5);
                            escribirFila(l5, palabra);
                            comprobacionCorrecta(l5, juego, palabra, palabraJuego);
                            break;
                    }

                    if (juego.isPartidaGanada() == false) {
                        juego.incrementarIntento();
                    }

                }
            }
        });

        if(juego.isPartidaGanada()==true){

        }


    }

    public void escribirFila(LinearLayout l, String palabra) {
        for (int i = 0; i < l.getChildCount(); i++) {
            TextView aux = (TextView) l.getChildAt(i);
            aux.setText(String.valueOf(palabra.charAt(i)).toUpperCase());
        }
    }

    public void comprobacionCorrecta(LinearLayout l, Juego juego, String palabra, String palabraJuego) {
        if (palabra.equalsIgnoreCase(palabraJuego)) {
            for (int i = 0; i < l.getChildCount(); i++) {
                TextView aux = (TextView) l.getChildAt(i);
                aux.setBackgroundColor(Color.parseColor("#019A01"));
            }
            juego.setPartidaGanada(true);
        } else {

                    for (int i = 0; i < l.getChildCount(); i++) {
                        TextView aux = (TextView) l.getChildAt(i);
                        char caracter1 = palabraJuego.charAt(i);
                        char caracter2 = palabra.charAt(i);

                        if (caracter2 == caracter1) {
                            aux.setBackgroundColor(Color.parseColor("#019A01"));
                        } else if (palabraJuego.indexOf(caracter2)!=-1) {
                            aux.setBackgroundColor(Color.parseColor("#ffc425"));
                        } else{
                            aux.setBackgroundColor(Color.parseColor("#737272"));
                        }
                }

        }

    }
}