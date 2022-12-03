package com.proyectodm.wordguesser.core;

/**
 * Clase que representa una partida
 */
public class Juego {
    private int id;
    private String dificultad;
    private String modo;
    private String idioma;
    private String palabra;
    private boolean resultado = false; //false DERROTA, true VICTORIA
    private int maximo_intentos;
    private int intentos = 0;

    public int getMaximo_intentos() {
        return maximo_intentos;
    }

    public Juego(int intentos, boolean resultado, int maximo_intentos,String modo, String dificultad, String idioma, String palabra){
        this.intentos=intentos;
        this.resultado=resultado;
        this.maximo_intentos=maximo_intentos;
        this.modo=modo;
        this.dificultad=dificultad;
        this.idioma=idioma;
        this.palabra = palabra;
    }

    public Juego() {

    }

    public int getIntentos(){
        return intentos;
    }

    public void incrementarIntento(){
        this.intentos++;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
