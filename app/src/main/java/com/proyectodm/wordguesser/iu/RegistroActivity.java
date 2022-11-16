package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.proyectodm.wordguesser.R;

import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class RegistroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText editNombre=(EditText)findViewById(R.id.editNombreRegistro);
        EditText editApellidos=(EditText)findViewById(R.id.editApellidosRegistro);
        EditText editUsuario=(EditText)findViewById(R.id.editUsuarioRegistro;
        EditText editPassword=(EditText)findViewById(R.id.editPasswordRegistro);
        EditText editRepetirPassword=(EditText)findViewById(R.id.editRepetirPasswordRegistro);

        Button registroButton=(Button)findViewById(R.id.registroButton);

        registroButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String apellidos = editApellidos.getText().toString();
                String usuario = editUsuario.getText().toString();
                String password = editPassword.getText().toString();
                String passwordRepetida = editRepetirPassword.getText().toString();
            }});

    }
}
