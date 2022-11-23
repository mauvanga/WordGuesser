package com.proyectodm.wordguesser.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Juego {
    int intentos=0;




    boolean partidaGanada=false;

    static String[] palabras;
    String palabraGanadora;
    String palabraUsuario;

public Juego(int intentos, boolean partidaGanada){
    intentos=this.intentos;
}

    public int getIntentos(){
    return intentos;
    }

    public void incrementarIntento(){
    this.intentos++;
    }

    String getPalabraGanadora(){
        return palabraGanadora;
    }

    String getPalabraUsuario(){
        return palabraUsuario;
    }

    public boolean isPartidaGanada() {
        return partidaGanada;
    }

    public void setPartidaGanada(boolean partidaGanada) {
        this.partidaGanada = partidaGanada;
    }

    //Comprueba si un caracter de la palabra introducida por el usuario es igual al de la palabra ganadora
    public String comprobarCaracter(String palabra, String palabraCorrecta, int n) {

        char caracter1= Character.toUpperCase(palabraGanadora.charAt(n));
        char caracter2= Character.toUpperCase(palabraUsuario.charAt(n));

        if(caracter2 == caracter1){
            return "verde";
        }

        else if(palabraCorrecta.indexOf(caracter2)!=-1)
            return "amarillo";

        return "gris";

    }

    //metodo que comprueba si la palabra que mete el usuario esta registrada en el diccionario
    public boolean comprobarPalabraExiste(String palabra)  {

        //String[] igualar=this.palabras;
//10836 seria el supuesto numero de palabras, pero eso lo obtenemos con un array.lenght

        for (int i = 0; i < 10836; i++) {
/*
            if (igualar[i].compareToIgnoreCase(palabraUsuario)==0) {
                return true;
                */
        }
        System.out.println("La palabra introducida ha de existir en el diccionario");
        return false;
    }

    //metodo para contar los intentos que lleva el jugador
    public boolean seguirJugando(int intentos) {
        if (intentos < 5) {
            return true;
        }
        return false;
    }

}
