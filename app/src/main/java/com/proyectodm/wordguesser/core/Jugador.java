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
//getters

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

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getMejorRacha() {
        return mejorRacha;
    }

    public void setMejorRacha(int mejorRacha) {
        this.mejorRacha = mejorRacha;
    }

    public int getRachaActual() {
        return rachaActual;
    }

    public void setRachaActual(int rachaActual) {
        this.rachaActual = rachaActual;
    }
}
