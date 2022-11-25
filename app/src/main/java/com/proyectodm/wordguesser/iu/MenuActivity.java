package com.proyectodm.wordguesser.iu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import com.proyectodm.wordguesser.R;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;


public class MenuActivity extends WordGuesserActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonNuevoJuego = (Button) findViewById(R.id.buttonNuevoJuego);
        Button buttonHistorialEstadistica = (Button) findViewById(R.id.buttonHistorialEstadistica);

        //boton que permite al usuario empezar una nueva partida
        buttonNuevoJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, GameActivity.class);
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

                                //volvemos a la actividad de login
                                Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.putExtra("EXIT", true);
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
