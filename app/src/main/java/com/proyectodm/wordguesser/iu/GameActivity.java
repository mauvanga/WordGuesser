package com.proyectodm.wordguesser.iu;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Juego;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends WordGuesserActivity {

    private Bundle bundle;
    private String modo_partida;
    private String lenguaje_partida;
    private String dificultad_partida;
    private int maximo_intentos;
    private final long TIEMPO_CUENTAATRAS_SEG = 200;
    private final long SEGUNDO = 1000;
    private final long MINUTO = 60;
    private final long PUNTO_PROGRESSBAR = 1;
    private boolean compartir = false;
    Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //variables obtenidas de SelectGameActivity
        bundle = getIntent().getExtras();
        modo_partida = bundle.getString("modo_partida");
        lenguaje_partida = bundle.getString("lenguaje_partida");
        dificultad_partida = bundle.getString("dificultad_partida");
        maximo_intentos = bundle.getInt("maximo_intentos");

        TextView tempText = null;
        ProgressBar progressBar = null;
        if(modo_partida.equalsIgnoreCase(CONTRARRELOJ)){
            if(dificultad_partida.equalsIgnoreCase(NORMAL) ){
                setContentView(R.layout.activity_juego_normal_time_trial); // Cargar la del modo correspondiente
                tempText = findViewById(R.id.textViewTemporizadorNormal);
                progressBar = findViewById(R.id.progressBarNormal);
            }
            if(dificultad_partida.equalsIgnoreCase(FACIL) ){
                setContentView(R.layout.activity_juego_facil_time_trial); // Cargar la del modo correspondiente
                tempText = findViewById(R.id.textViewTemporizadorFacil);
                progressBar = findViewById(R.id.progressBarFacil);
            }
            if(dificultad_partida.equalsIgnoreCase(DIFICIL) ){
                setContentView(R.layout.activity_juego_dificil_time_trial); // Cargar la del modo correspondiente
                tempText = findViewById(R.id.textViewTemporizadorDificil);
                progressBar = findViewById(R.id.progressBarDificil);
            }
        } else {
            //dependiendo de la dificultad escogida se mostrara un layout u otro debido a que el numero de filas cambia
            if(dificultad_partida.equalsIgnoreCase(NORMAL) ){
                setContentView(R.layout.activity_juego_normal); // Cargar la del modo correspondiente
            }
            if(dificultad_partida.equalsIgnoreCase(FACIL) ){
                setContentView(R.layout.activity_juego_facil); // Cargar la del modo correspondiente
            }
            if(dificultad_partida.equalsIgnoreCase(DIFICIL) ){
                setContentView(R.layout.activity_juego_dificil); // Cargar la del modo correspondiente
            }
        }

        EditText palabraUsuario = (EditText) findViewById(R.id.palabraUsuario);

        String palabraJuego;
        List<String> listaPalabras = new ArrayList<>();
        List<String> cientifico = new ArrayList<>();

        try {
            String line;
            InputStream in = null;
            if (lenguaje_partida.equalsIgnoreCase(GALLEGO)) {
                in = this.getAssets().open("gl_5.txt");
            }
            if (lenguaje_partida.equalsIgnoreCase(INGLES)) {
                in = this.getAssets().open("en_5.txt");
            }
            if (lenguaje_partida.equalsIgnoreCase(CASTELLANO)) {
                in = this.getAssets().open("es_5.txt");
            }
            BufferedReader inf = new BufferedReader(new InputStreamReader(in));
            while ((line = inf.readLine()) != null) {
                listaPalabras.add(line);
            }
        } catch (IOException e) {
            listaPalabras.add("error");
        }

        //Leemos los ficheros de texto con las palabras cientificas si corresponde
        if(modo_partida.equalsIgnoreCase(CIENTIFICO)) {
            try {
                String line;
                InputStream in = null;
                if (lenguaje_partida.equalsIgnoreCase(GALLEGO)) {
                    in = this.getAssets().open("CIENTIFICO_GALEGO_30.txt");
                }
                if (lenguaje_partida.equalsIgnoreCase(INGLES)) {
                    in = this.getAssets().open("CIENTIFICO_ENGLISH_30.txt");
                }
                if (lenguaje_partida.equalsIgnoreCase(CASTELLANO)) {
                    in = this.getAssets().open("CIENTIFICO_ESPANOL_30.txt");
                }
                BufferedReader inf = new BufferedReader(new InputStreamReader(in));
                while ((line = inf.readLine()) != null) {
                    cientifico.add(line);
                }
            } catch (IOException e) {
                cientifico.add("error");
            }
            palabraJuego = cientifico.get((int) (Math.random() * cientifico.size()));
        } else {
            palabraJuego = listaPalabras.get((int) (Math.random() * listaPalabras.size()));
        }

        Button buttonAceptarJuegoClasico = findViewById(R.id.buttonAceptarJuegoClasico);

        int intentos = 0;

        //inicializamos el constructor del objeto juego que representara la partida actual
        juego = new Juego(intentos, false, maximo_intentos, modo_partida, dificultad_partida, lenguaje_partida, palabraJuego);

        if(modo_partida.equalsIgnoreCase(CONTRARRELOJ)) {
            TextView finalTempText = tempText;
            ProgressBar finalProgressBar = progressBar;
            CountDownTimer countDownTimer = new CountDownTimer(TIEMPO_CUENTAATRAS_SEG*SEGUNDO,SEGUNDO) {
                int cont = 100;
                boolean decrementar = false;
                @Override
                public void onTick(long millisUntilFinished) {
                    long second = (millisUntilFinished / SEGUNDO) % MINUTO;
                    long minute = (millisUntilFinished / (SEGUNDO * MINUTO)) % MINUTO;

                    String time = String.format("%02d:%02d", minute, second);
                    finalTempText.setText(time);
                    if (!decrementar){
                        cont -= PUNTO_PROGRESSBAR;
                        decrementar = true;
                    } else{
                        decrementar = false;
                    }
                    finalProgressBar.setProgress(cont,true);
                }

                @Override
                public void onFinish() {
                    partidaPerdida(palabraJuego, juego, getString(R.string.derrota_time_trial));
                }
            };
            countDownTimer.start();
        }

        buttonAceptarJuegoClasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String palabra = palabraUsuario.getText().toString();

                //comprobamos que la palabra sea de longitud 5
                if (palabra.length() != 5) {
                    Snackbar.make(findViewById(R.id.gameLayout), getString(R.string.must_5_letters), Snackbar.LENGTH_SHORT).show();
                } else if(!listaPalabras.contains(palabra.toLowerCase()) && !cientifico.contains(palabra.toLowerCase())){
                    Snackbar.make(findViewById(R.id.gameLayout), getString(R.string.word_not_valid), Snackbar.LENGTH_SHORT).show();
                } else {
                    //segun el numero de intentos que lleve el usuario se comprobara una fila u otra
                    //el numero de filas va hasta 7 ya que es el numero de intentos maximo que puede llegar a tener una partida
                    switch (juego.getIntentos()) {
                        case 0:
                            LinearLayout l = (LinearLayout) findViewById(R.id.fila1);
                            escribirFila(l, palabra);
                            comprobacionCorrecta(l, juego, palabra, palabraJuego);
                            break;

                        case 1:
                            LinearLayout l2 = (LinearLayout) findViewById(R.id.fila2);
                            escribirFila(l2, palabra);
                            comprobacionCorrecta(l2, juego, palabra, palabraJuego);
                            break;

                        case 2:
                            LinearLayout l3 = (LinearLayout) findViewById(R.id.fila3);
                            escribirFila(l3, palabra);
                            comprobacionCorrecta(l3, juego, palabra, palabraJuego);
                            break;

                        case 3:
                            LinearLayout l4 = (LinearLayout) findViewById(R.id.fila4);
                            escribirFila(l4, palabra);
                            comprobacionCorrecta(l4, juego, palabra, palabraJuego);
                            break;

                        case 4:
                            LinearLayout l5 = (LinearLayout) findViewById(R.id.fila5);
                            escribirFila(l5, palabra);
                            comprobacionCorrecta(l5, juego, palabra, palabraJuego);
                            break;

                        case 5:
                            LinearLayout l6 = (LinearLayout) findViewById(R.id.fila6);
                            escribirFila(l6, palabra);
                            comprobacionCorrecta(l6, juego, palabra, palabraJuego);
                            break;

                        case 6:
                            LinearLayout l7 = (LinearLayout) findViewById(R.id.fila7);
                            escribirFila(l7, palabra);
                            comprobacionCorrecta(l7, juego, palabra, palabraJuego);
                            break;
                    }
                    //si el jugador no ha acertado la palabra, se incrementa un intento
                    if (!juego.getResultado()) {
                        juego.incrementarIntento();
                    }

                    //si el jugador agota el numero de intentos correspondiente a la dificultad escogida significa que ha perdido
                    if(juego.getIntentos()==juego.getMaximo_intentos()){
                        partidaPerdida(palabraJuego, juego, getString(R.string.derrota_msg));
                    }

                    //si el jugador ha acertado la palabra antes de agotar el numero de intentos:
                    if(juego.getResultado()){
                        getDbManager().addResultGame(palabraJuego, juego.getModo(), juego.getDificultad() , juego.getIdioma(), true, getJugadorLogueado().getIdJugador());
                        int nuevaRachaActual = getJugadorLogueado().getRachaActual() + 1;
                        if (getDbManager().editPlayerRacha(getJugadorLogueado().getIdJugador(), nuevaRachaActual)){
                            getJugadorLogueado().setRachaActual(nuevaRachaActual);
                        }
                        if (getJugadorLogueado().getMejorRacha() < nuevaRachaActual){
                            if (getDbManager().editPlayerMejorRacha(getJugadorLogueado().getIdJugador(), nuevaRachaActual)){
                                getJugadorLogueado().setMejorRacha(nuevaRachaActual);
                            }
                        }
                        AlertDialog.Builder ganado = new AlertDialog.Builder(GameActivity.this);
                        ganado.setMessage(getString(R.string.victoria_msg)).setCancelable(false)
                                .setPositiveButton(getString(R.string.play_again), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //volvemos a la seleccion de juego
                                        finish();
                                    }
                                })
                                .setNeutralButton(getString(R.string.share), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        compartirRedesSociales();
                                    }
                                })
                                .setNegativeButton(getString(R.string.return_menu), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //volvemos a la actividad de menú
                                        Intent i = new Intent(GameActivity.this, MenuActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                        AlertDialog alert = ganado.create();
                        alert.show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // El flag compartir se activa cuando un jugador acaba la partida y le da a compartir
        if (compartir) {
            AlertDialog.Builder perdido = new AlertDialog.Builder(GameActivity.this);
            String msg = "";
            if (!juego.getResultado()) {
                if (juego.getModo().equalsIgnoreCase(CONTRARRELOJ)){
                    msg = getString(R.string.derrota_time_trial);
                } else {
                    msg = getString(R.string.derrota_msg);
                }
                msg = msg + " " + juego.getPalabra();
            } else{
                msg = getString(R.string.victoria_msg);
            }
            perdido.setMessage(msg).setCancelable(false)
                    .setPositiveButton(getString(R.string.play_again), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //cerramos el juego para volver a la actividad de selección de juego
                            finish();
                        }
                    })
                    .setNeutralButton(getString(R.string.share), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            compartirRedesSociales();
                        }
                    })
                    .setNegativeButton(getString(R.string.return_menu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //volvemos a la actividad de menú
                            Intent i = new Intent(GameActivity.this, MenuActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }
                    });

            AlertDialog alert = perdido.create();
            alert.show();
        }
    }

    private void compartirRedesSociales() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        StringBuilder msg = new StringBuilder();
        msg.append(getString(R.string.last_game_result)).append("\n");
        msg.append("- ").append(getTranslatedText(IDIOMA)).append(" : ").append(getTranslatedText(juego.getIdioma())).append("\n");
        msg.append("- ").append(getTranslatedText(MODO)).append(" : ").append(getTranslatedText(juego.getModo())).append("\n");
        msg.append("- ").append(getTranslatedText(DIFICULTAD)).append(" : ").append(getTranslatedText(juego.getDificultad())).append("\n");
        msg.append("- ").append(getTranslatedText(RESULTADO)).append(" : ").append(getTranslatedText(juego.getResultado() ? VICTORIA : DERROTA)).append("\n");
        if (juego.getResultado()){
            msg.append(getJugadorLogueado().getRachaActual()).append(" ").append(getString(R.string.victorias_seguidas)).append("\n");
        }
        msg.append("\n").append(getString(R.string.play_you_too));
        msg.append("\n").append("https://github.com/mauvanga/WordGuesser");
        String text = msg.toString();
        i.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(i,getString(R.string.share)));
        compartir = true;
    }

    private void partidaPerdida(String palabraJuego, Juego juego, String derrota) {
        getDbManager().addResultGame(palabraJuego, juego.getModo(), juego.getDificultad() , juego.getIdioma(), false, getJugadorLogueado().getIdJugador());
        if (getDbManager().editPlayerRacha(getJugadorLogueado().getIdJugador(), 0)){
            getJugadorLogueado().setRachaActual(0);
        }
        AlertDialog.Builder perdido = new AlertDialog.Builder(GameActivity.this);
        perdido.setMessage(derrota + " " + palabraJuego).setCancelable(false)
                .setPositiveButton(getString(R.string.play_again), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //cerramos el juego para volver a la actividad de selección de juego
                        finish();
                    }
                })
                .setNeutralButton(getString(R.string.share), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        compartirRedesSociales();
                    }
                })
                .setNegativeButton(getString(R.string.return_menu), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //volvemos a la actividad de menú
                        Intent i = new Intent(GameActivity.this, MenuActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                });

        AlertDialog alert = perdido.create();
        alert.show();
    }

    //escribimos las letras de la palabra introducida por el usuario en una fila
    public void escribirFila(LinearLayout l, String palabra) {
        for (int i = 0; i < l.getChildCount(); i++) {
            TextView aux = (TextView) l.getChildAt(i);
            aux.setText(String.valueOf(palabra.charAt(i)).toUpperCase());
        }
    }

    //comprobamos que la palabra del usuario sea correcta
    public void comprobacionCorrecta(LinearLayout l, Juego juego, String palabra, String palabraJuego) {
        if (palabra.equalsIgnoreCase(palabraJuego)) {
            for (int i = 0; i < l.getChildCount(); i++) {
                TextView aux = (TextView) l.getChildAt(i);
                aux.setBackgroundColor(Color.parseColor("#019A01"));
            }
            juego.setResultado(true);
        } else {
            for (int i = 0; i < l.getChildCount(); i++) {
                TextView aux = (TextView) l.getChildAt(i);
                char caracter1 = palabraJuego.charAt(i);
                char caracter2 = palabra.charAt(i);

                //si la letra esta en la misma posicion, el cuadrado correspondiente se pone en verde
                if (caracter2 == caracter1) {
                    aux.setBackgroundColor(Color.parseColor("#019A01"));
                }
                //si la letra esta en la palabra, pero no en la misma posicion, el cuadrado correspondiente se pone en naranja
                else if (palabraJuego.indexOf(caracter2)!=-1) {
                    aux.setBackgroundColor(Color.parseColor("#ffc425"));
                }
                //si la letra no esta en la palabra, el cuadrado correspondiente se pone en gris
                else{
                    aux.setBackgroundColor(Color.parseColor("#737272"));
                }
            }
        }
    }
}