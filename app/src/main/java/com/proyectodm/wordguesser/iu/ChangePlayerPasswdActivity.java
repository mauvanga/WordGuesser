package com.proyectodm.wordguesser.iu;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        EditText editTextOldPasswd = findViewById(R.id.editTextOldPasswd);
        EditText editTextNewPasswd = findViewById(R.id.editTextNewPasswd);
        EditText editTextNewPasswdRepeat = findViewById(R.id.editTextNewPasswdRepeat);

        Button buttonChangePasswd = findViewById(R.id.buttonChangePasswd);
        buttonChangePasswd.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextOldPasswd.getText().toString().trim().length() > 0 &&
                        editTextOldPasswd.getText().toString().trim().length() > 0 &&
                        editTextNewPasswdRepeat.getText().toString().trim().length() > 0){
                    buttonChangePasswd.setEnabled(true);
                }else{
                    buttonChangePasswd.setEnabled(false);
                    Snackbar.make(findViewById(R.id.changePlayerPasswdLayout), getString(R.string.campos_no_vacios), Snackbar.LENGTH_SHORT).show();
                }
            }
        };
        editTextOldPasswd.addTextChangedListener(textWatcher);
        editTextNewPasswd.addTextChangedListener(textWatcher);
        editTextNewPasswdRepeat.addTextChangedListener(textWatcher);

        buttonChangePasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPasswd = editTextOldPasswd.getText().toString();
                String newPasswd = editTextNewPasswd.getText().toString();
                String newPasswdRepeat = editTextNewPasswdRepeat.getText().toString();

                if (oldPasswd.equals(getJugadorLogueado().getContraseña())){
                    if (newPasswd.equals(newPasswdRepeat)){
                        if (getDbManager().editPlayerPassword(getJugadorLogueado().getIdJugador(), newPasswd)){
                            getJugadorLogueado().setContraseña(newPasswd);
                            setJugadorLogueado(getJugadorLogueado());
                            finish();
                        } else {
                            Snackbar.make(findViewById(R.id.changePlayerPasswdLayout), getString(R.string.error_edit_passwd), Snackbar.LENGTH_SHORT).show();
                        }
                    } else{
                        Snackbar.make(findViewById(R.id.changePlayerPasswdLayout), getString(R.string.new_passwd_not_match), Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.changePlayerPasswdLayout), getString(R.string.old_passwd_not_match), Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }
}
