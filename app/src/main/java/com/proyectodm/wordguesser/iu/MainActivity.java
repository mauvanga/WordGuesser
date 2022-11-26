package com.proyectodm.wordguesser.iu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.proyectodm.wordguesser.R;

public class MainActivity extends WordGuesserActivity {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultContract<Intent, ActivityResult> contract =
                new ActivityResultContracts.StartActivityForResult();
        ActivityResultCallback<ActivityResult> callback =
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            goToMenuActivity();
                        } else if (result.getResultCode() == Activity.RESULT_CANCELED){
                            finish();
                        }
                    }
                };
        activityResultLauncher = registerForActivityResult(contract, callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isJugadorLogueado()){
            goToMenuActivity();
        } else {
            goToLoginActivity();
        }
    }

    private void goToMenuActivity(){
        activityResultLauncher.launch(new Intent(this, MenuActivity.class));
        //this.startActivity(new Intent(this, MenuActivity.class));
        // todo preguntarle al profesor la solución actual es un poco bruta
        //  ya que al finalizar el menu se volvía a lanzar el menu en el onResume()
        //  una vez que te registras tienes que darle 2 veces a atrás para salir
        //  pero ya no se genera un bucle
    }

    private void goToLoginActivity(){
        activityResultLauncher.launch(new Intent(this, LoginActivity.class));
    }
}