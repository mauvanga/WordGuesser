package com.proyectodm.wordguesser.core;

public class Jugador {
    private int idJugador;
    private String nombre;
    private String apellidos;
    private String usuario;
    private String contraseña;
    private int mejorRacha;
    private int rachaActual;

    public Jugador(int idJugador, String nombre, String apellidos, String usuario, String contraseña, int mejorRacha, int rachaActual) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.mejorRacha = mejorRacha;
        this.rachaActual = rachaActual;
    }

    public int getIdJugador() {
        return idJugador;
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

    public int getRachaActual() {
        return rachaActual;
    }

    public int getMejorRacha() {
        return mejorRacha;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setMejorRacha(int mejorRacha) {
        this.mejorRacha = mejorRacha;
    }

    public void setRachaActual(int rachaActual) {
        this.rachaActual = rachaActual;
    }
}
