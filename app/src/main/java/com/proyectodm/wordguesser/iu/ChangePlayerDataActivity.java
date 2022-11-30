package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;

public class ChangePlayerDataActivity extends WordGuesserActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_change_data);
        EditText editTextPlayerName = findViewById(R.id.editTextCurrentPlayerName);
        EditText editTextPlayerSurname = findViewById(R.id.editTextCurrentPlayerSurname);
        EditText editTextPlayerUsername = findViewById(R.id.editTextCurrentPlayerUsername);
        editTextPlayerName.setText(getJugadorLogueado().getNombre());
        editTextPlayerSurname.setText(getJugadorLogueado().getApellidos());
        editTextPlayerUsername.setText(getJugadorLogueado().getUsuario());

        Button buttonChangeData = findViewById(R.id.buttonChangeData);
        buttonChangeData.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextPlayerName.getText().toString().trim().length() > 0 &&
                        editTextPlayerSurname.getText().toString().trim().length() > 0 &&
                        editTextPlayerUsername.getText().toString().trim().length() > 0){
                    buttonChangeData.setEnabled(true);
                }else{
                    buttonChangeData.setEnabled(false);
                    Snackbar.make(findViewById(R.id.changePlayerDataLayout), getString(R.string.campos_no_vacios), Snackbar.LENGTH_SHORT).show();
                }
            }
        };
        editTextPlayerName.addTextChangedListener(textWatcher);
        editTextPlayerSurname.addTextChangedListener(textWatcher);
        editTextPlayerUsername.addTextChangedListener(textWatcher);

        buttonChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = editTextPlayerName.getText().toString();
                String nuevoApellido = editTextPlayerSurname.getText().toString();
                String nuevoUsername = editTextPlayerUsername.getText().toString().toLowerCase();

                if (getDbManager().editPlayerData(getJugadorLogueado().getIdJugador(),
                        nuevoNombre,nuevoApellido,nuevoUsername)){
                    getJugadorLogueado().setNombre(nuevoNombre);
                    getJugadorLogueado().setApellidos(nuevoApellido);
                    getJugadorLogueado().setUsuario(nuevoUsername);
                    setJugadorLogueado(getJugadorLogueado());
                    finish();
                } else {
                    Snackbar.make(findViewById(R.id.changePlayerDataLayout), getString(R.string.username_cannot_edit), Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }
}
