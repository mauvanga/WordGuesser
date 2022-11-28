package com.proyectodm.wordguesser.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.proyectodm.wordguesser.R;
import android.content.DialogInterface;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;


public class MenuActivity extends WordGuesserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonNuevoJuego = findViewById(R.id.buttonNuevoJuego);
        Button buttonHistorialEstadistica = findViewById(R.id.buttonHistorialEstadistica);
        Button buttonComoJugar = findViewById(R.id.buttonComoJugar);

        //boton que permite al usuario empezar una nueva partida
        buttonNuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, SelectGameActivity.class);
                startActivity(i);
            }
        });

        //boton que permite al usuario acceder al historial de partidas
        buttonHistorialEstadistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, HistorialActivity.class);
                startActivity(i);
            }
        });

        //boton que lleva al usuario a una explicacion del juego
        buttonComoJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, ComoJugarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textViewActualUsername = findViewById(R.id.textViewActualUsername);
        textViewActualUsername.setText(getJugadorLogueado().getUsuario());
    }

    //Creacion de menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu( menu );

        this.getMenuInflater().inflate( R.menu.actions_menu, menu );
        return true;
    }

    //asignacion de las opciones al menu
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        boolean toret = false;

        switch( menuItem.getItemId() ) {
            case R.id.configuracionMenu:
                Intent i = new Intent(MenuActivity.this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case R.id.cerrarSesionMenu:
                AlertDialog.Builder seguroCerrarSesion = new AlertDialog.Builder(MenuActivity.this);
                seguroCerrarSesion.setMessage("¿Seguro que quieres cerrar la sesión?").setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // volvemos a la actividad MainActivity que nos llevará al LoginActivity
                                // ya que no habrá usuario conectado
                                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                clearJugadorLogueado();
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = seguroCerrarSesion.create();
               // alert.setTitle("...");
                alert.show();
                break;
        }

        return toret;
    }
}
