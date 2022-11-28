package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;

public class ChangePlayerPasswdActivity extends WordGuesserActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_change_passwd);
        EditText editTextPlayerName = findViewById(R.id.editTextCurrentPlayerName);
        EditText editTextPlayerSurname = findViewById(R.id.editTextCurrentPlayerSurname);
        EditText editTextPlayerUsername = findViewById(R.id.editTextCurrentPlayerUsername);

        Button buttonChangeData = findViewById(R.id.buttonChangeData);
        buttonChangeData.setEnabled(false);
        buttonChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = editTextPlayerName.getText().toString();
                String nuevoApellido = editTextPlayerSurname.getText().toString();
                String nuevoUsername = editTextPlayerUsername.getText().toString();

                if (getDbManager().editPlayerData(getJugadorLogueado().getIdJugador(),
                        nuevoNombre,nuevoApellido,nuevoUsername)){
                    getJugadorLogueado().setNombre(nuevoNombre);
                    getJugadorLogueado().setApellidos(nuevoApellido);
                    getJugadorLogueado().setUsuario(nuevoUsername);
                    setJugadorLogueado(getJugadorLogueado());
                    finish();
                } else {
                    Snackbar.make(findViewById(R.id.changePlayerDataLayout), "No se ha podido modificar el usuario", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }
}
