package com.proyectodm.wordguesser.core;

public class Juego {
    int intentos=0;
    String palabraGanadora;
    String palabraUsuario;

    int getIntentos(){
        return intentos;
    }

    String getPalabraGanadora(){
        return palabraGanadora;
    }

    String getPalabraUsuario(){
        return palabraUsuario;
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
