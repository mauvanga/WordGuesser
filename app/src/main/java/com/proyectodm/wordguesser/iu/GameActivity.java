package com.proyectodm.wordguesser.iu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import com.proyectodm.wordguesser.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int intento=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_clasico_normal); // Cargar la del modo correspondiente

        EditText palabraUsuarioClasicoNormal=(EditText)findViewById(R.id.palabraUsuarioClasicoNormal);
        TextView cuadroClasicoNormal1 = (TextView) this.findViewById( R.id.cuadroClasicoNormal1 );
        TextView cuadroClasicoNormal2 = (TextView) this.findViewById( R.id.cuadroClasicoNormal2 );
        TextView cuadroClasicoNormal3 = (TextView) this.findViewById( R.id.cuadroClasicoNormal3 );
        TextView cuadroClasicoNormal4 = (TextView) this.findViewById( R.id.cuadroClasicoNormal4);
        TextView cuadroClasicoNormal5 = (TextView) this.findViewById( R.id.cuadroClasicoNormal5);

        Button buttonAceptarJuegoClasico = (Button)findViewById(R.id.buttonAceptarJuegoClasico);

        buttonAceptarJuegoClasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String palabra = palabraUsuarioClasicoNormal.getText().toString();

                if (!(palabra.length() == 5)) {

                } else {
                    if (intento == 0) {
                        cuadroClasicoNormal1.setText(String.valueOf(palabra.charAt(0)));
                        cuadroClasicoNormal2.setText(String.valueOf(palabra.charAt(1)));
                        cuadroClasicoNormal3.setText(String.valueOf(palabra.charAt(2)));
                        cuadroClasicoNormal4.setText(String.valueOf(palabra.charAt(3)));
                        cuadroClasicoNormal5.setText(String.valueOf(palabra.charAt(4)));

                        //verde
                        cuadroClasicoNormal1.setBackgroundColor(Color.parseColor("#019A01"));
                        cuadroClasicoNormal2.setBackgroundColor(Color.parseColor("#019A01"));
                        //naranja
                        cuadroClasicoNormal3.setBackgroundColor(Color.parseColor("#ffc425"));
                        //gris
                        cuadroClasicoNormal4.setBackgroundColor(Color.parseColor("#BCB9B9"));
                        cuadroClasicoNormal5.setBackgroundColor(Color.parseColor("#ffc425"));
                    }

                    if(intento==1){

                    }

                    if(intento==2){

                    }

                    if(intento==3){

                    }

                    if(intento==4){

                    }

                }
            }
        });
    }



}
