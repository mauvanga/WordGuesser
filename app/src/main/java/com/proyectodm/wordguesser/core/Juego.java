package com.proyectodm.wordguesser.core;

public class Juego {
    private int id;
    private String dificultad;
    private String modo;
    private String idioma;
    private String palabra;
    private boolean resultado; //false derrota, true victoria

    int intentos=0;

    public int getMaximo_intentos() {
        return maximo_intentos;
    }

    int maximo_intentos;
    boolean partidaGanada=false;

    String palabraGanadora;
    String palabraUsuario;

    public Juego(int intentos, boolean partidaGanada, int maximo_intentos,String modo, String dificultad, String idioma){
        this.intentos=intentos;
        this.partidaGanada=partidaGanada;
        this.maximo_intentos=maximo_intentos;
        this.modo=modo;
        this.dificultad=dificultad;
        this.idioma=idioma;
    }

    public Juego() {

    }

    public int getIntentos(){
        return intentos;
    }

    public void incrementarIntento(){
        this.intentos++;
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
