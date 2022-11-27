package com.proyectodm.wordguesser.iu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Jugador;

public class LoginActivity extends WordGuesserActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername = findViewById(R.id.editTextTextUsername);
        EditText editTextPassword = findViewById(R.id.editTextTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegistro = findViewById(R.id.buttonRegistro);
        buttonLogin.setEnabled(false);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                Jugador jugador = getDbManager().checkLogin(usuario, password);
                if(jugador != null){
                    setJugadorLogueado(jugador);
                    LoginActivity.this.setResult(Activity.RESULT_OK);
                    LoginActivity.this.finish();
                }else{
                    Snackbar.make(findViewById(R.id.loginLayout), getString(R.string.username_passwd_dont_match), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextUsername.getText().toString().trim().length() > 0 &&
                        editTextPassword.getText().toString().trim().length() > 0){
                    buttonLogin.setEnabled(true);
                }else{
                    buttonLogin.setEnabled(false);
                    Snackbar.make(findViewById(R.id.loginLayout), getString(R.string.campos_no_vacios), Snackbar.LENGTH_SHORT).show();
                }
            }
        };
        editTextUsername.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
    }

}
