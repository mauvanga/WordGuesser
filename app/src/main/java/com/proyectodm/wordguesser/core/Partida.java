package com.proyectodm.wordguesser.core;

public class Partida {
    String dificultad;
    String modo;
    String idioma;
    int resultado; //0 derrota, 1 victoria

    public Partida(String dificultad, String modo, String idioma, int resultado) {
        this.dificultad = dificultad;
        this.modo = modo;
        this.idioma = idioma;
        this.resultado = resultado;
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

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
