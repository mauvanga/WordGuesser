package com.proyectodm.wordguesser.iu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;


public class ConfiguracionActivity extends WordGuesserActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        Button buttonModifyData = findViewById(R.id.buttonModificarDatos);
        Button buttonChangePassword = findViewById(R.id.buttonCambiarPass);
        Button buttonDeletePlayer = findViewById(R.id.buttonEliminarCuenta);

        buttonModifyData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfiguracionActivity.this, ChangePlayerDataActivity.class);
                startActivity(i);
            }
        });

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfiguracionActivity.this, ChangePlayerPasswdActivity.class);
                startActivity(i);
            }
        });

        buttonDeletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder seguroBorrarUsuario = new AlertDialog.Builder(ConfiguracionActivity.this);
                seguroBorrarUsuario.setMessage("¿Seguro que quieres borrar el usuario?").setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // volvemos a la actividad MainActivity que nos llevará al LoginActivity
                                // ya que no habrá usuario conectado
                                Intent i = new Intent(ConfiguracionActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                getDbManager().deletePlayer(getJugadorLogueado().getIdJugador());
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

                seguroBorrarUsuario.create().show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textViewName = findViewById(R.id.textViewCurrentPlayerName);
        TextView textViewSurname = findViewById(R.id.textViewCurrentPlayerSurname);
        TextView textViewUsername = findViewById(R.id.textViewCurrentPlayerUsername);

        textViewName.setText(getJugadorLogueado().getNombre());
        textViewSurname.setText(getJugadorLogueado().getApellidos());
        textViewUsername.setText(getJugadorLogueado().getUsuario());
    }
}
