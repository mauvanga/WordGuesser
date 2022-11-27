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
        Intent i = new Intent(this, MenuActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(i);
        finish();
    }

    private void goToLoginActivity(){
        activityResultLauncher.launch(new Intent(this, LoginActivity.class));
    }
}