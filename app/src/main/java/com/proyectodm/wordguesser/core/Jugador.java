package com.proyectodm.wordguesser.core;

public class Jugador {

    //variables que considero importantes en esta clase
    private int partidas = 0; //Cambiar por racha actual
    private int victorias = 0; //Cambiar por mejor racha
    private int perdidas = 0; //Eliminar esto se calcula a partir de la db

    private int idJugador;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String contraseña;

    //getters

    public int getIdJugador() {
        return idJugador;
    }

    public int getPartidas(){
        return partidas;
    }

    public int getVictorias(){
        return victorias;
    }

    public int getPerdidas(){
        return perdidas;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContraseña(){
        return contraseña;
    }

    //setters

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    //metodo que devuelve la mejor racha
    public int calcularMejorRacha(){
        int racha=0;
        return racha;
    }

    //metodo que devuelve la racha actual
    public int calcularRachaActual(){
        int racha=0;
        return racha;
    }

}
