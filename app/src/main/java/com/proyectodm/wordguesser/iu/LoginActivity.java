package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.proyectodm.wordguesser.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editTextUsername=(EditText)findViewById(R.id.editTextTextUsername);
        EditText editTextPassword=(EditText)findViewById(R.id.editTextTextPassword);

        Button buttonLogin=(Button)findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextUsername.getText().toString();
                String pass = editTextPassword.getText().toString();
            }
        });

    }

}
