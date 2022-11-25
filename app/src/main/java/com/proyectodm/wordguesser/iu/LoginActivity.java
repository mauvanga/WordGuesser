package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.core.Jugador;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends WordGuesserActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername = (EditText) findViewById(R.id.editTextTextUsername);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Button buttonRegistro = (Button) findViewById(R.id.buttonRegistro);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                Jugador jugador = getDbManager().checkLogin(usuario, password);
                if(jugador != null){
                    Toast.makeText(getApplicationContext(), getString(R.string.user_logged), Toast.LENGTH_SHORT).show(); //ASDStodo cambiar Snakbar
                    setJugadorLogueado(jugador);
                    LoginActivity.this.setResult(Activity.RESULT_OK);
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(getApplicationContext(),"El nombre de usuario o la contraseña están incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });

    }

}
