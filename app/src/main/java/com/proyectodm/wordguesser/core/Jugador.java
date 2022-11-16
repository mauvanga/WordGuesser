package com.proyectodm.wordguesser.core;

public class Jugador {

    //variables que considero importantes en esta clase
    int partidas = 0;
    int victorias = 0;
    int perdidas = 0;

    //crear una clase persona y que jugador herede de ella?
    String nombre;
    String apellidos;
    String usuario;
    String contraseña;

    //getters

    int getPartidas(){
        return partidas;
    }

    int getVictorias(){
        return victorias;
    }

    int getPerdidas(){
        return perdidas;
    }

    String getNombre(){
        return nombre;
    }

    String getApellidos(){
        return apellidos;
    }

    String getUsuario(){
        return usuario;
    }

    String getContraseña(){
        return contraseña;
    }

    //setters

    void setNombre(String nombre){
        this.nombre=nombre;
    }

    void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    void setUsuario(String usuario){
        this.usuario = usuario;
    }

    //metodo que devuelve la mejor racha
    int calcularMejorRacha(){
        int racha=0;
        return racha;
    }

    //metodo que devuelve la racha actual
    int calcularRachaActual(){
        int racha=0;
        return racha;
    }

}
