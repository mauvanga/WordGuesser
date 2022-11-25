package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.proyectodm.wordguesser.R;
import com.proyectodm.wordguesser.database.DBManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    private DBManager gestorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        this.gestorDB = new DBManager(this.getApplicationContext());

        EditText editNombre=(EditText)findViewById(R.id.editNombreRegistro);
        EditText editApellidos=(EditText)findViewById(R.id.editApellidosRegistro);
        EditText editUsuario=(EditText)findViewById(R.id.editUsuarioRegistro);
        EditText editPassword=(EditText)findViewById(R.id.editPasswordRegistro);
        EditText editRepetirPassword=(EditText)findViewById(R.id.editRepetirPasswordRegistro);

        Button registroButton=(Button)findViewById(R.id.registroButton);
        registroButton.setEnabled(false);

        registroButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String apellidos = editApellidos.getText().toString();
                String usuario = editUsuario.getText().toString();
                String password = editPassword.getText().toString();
                String passwordRepetida = editRepetirPassword.getText().toString();
                if (password.equals(passwordRepetida)){
                    if(gestorDB.registerPlayer(usuario, password, nombre, apellidos)){
                        Toast.makeText(getApplicationContext(),"Usuario registrado sin problemas", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"El usuario ya está registrado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }});


        editNombre.addTextChangedListener(new TextWatcher() {
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
                }
            }
        });

        editApellidos.addTextChangedListener(new TextWatcher() {
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
                }
            }
        });

        editUsuario.addTextChangedListener(new TextWatcher() {
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
                }
            }
        });

        editPassword.addTextChangedListener(new TextWatcher() {
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
                }
            }
        });

        editRepetirPassword.addTextChangedListener(new TextWatcher() {
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
                }
            }
        });

    }
}
