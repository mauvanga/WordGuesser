package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;

public class RegistroActivity extends WordGuesserActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText editNombre = findViewById(R.id.editNombreRegistro);
        EditText editApellidos = findViewById(R.id.editApellidosRegistro);
        EditText editUsuario = findViewById(R.id.editUsuarioRegistro);
        EditText editPassword = findViewById(R.id.editPasswordRegistro);
        EditText editRepetirPassword = findViewById(R.id.editRepetirPasswordRegistro);

        Button registroButton = findViewById(R.id.registroButton);
        registroButton.setEnabled(false);

        registroButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String apellidos = editApellidos.getText().toString();
                String usuario = editUsuario.getText().toString().toLowerCase();
                String password = editPassword.getText().toString();
                String passwordRepetida = editRepetirPassword.getText().toString();
                if (password.equals(passwordRepetida)){
                    if(getDbManager().registerPlayer(usuario, password, nombre, apellidos)){
                        RegistroActivity.this.finish();
                    }else{
                        Snackbar.make(findViewById(R.id.registroLayout), getString(R.string.username_exist), Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.registroLayout), getString(R.string.dont_match_passwd), Snackbar.LENGTH_SHORT).show();
                }
            }});

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editNombre.getText().toString().trim().length() > 0 &&
                        editApellidos.getText().toString().trim().length() > 0 &&
                        editUsuario.getText().toString().trim().length() > 0 &&
                        editPassword.getText().toString().trim().length() > 0 &&
                        editRepetirPassword.getText().toString().trim().length() > 0){
                    registroButton.setEnabled(true);
                }else{
                    registroButton.setEnabled(false);
                    Snackbar.make(findViewById(R.id.registroLayout), getString(R.string.campos_no_vacios), Snackbar.LENGTH_SHORT).show();
                }
            }
        };
        editNombre.addTextChangedListener(textWatcher);
        editApellidos.addTextChangedListener(textWatcher);
        editUsuario.addTextChangedListener(textWatcher);
        editPassword.addTextChangedListener(textWatcher);
        editRepetirPassword.addTextChangedListener(textWatcher);
    }
}
