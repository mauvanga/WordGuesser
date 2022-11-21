package com.proyectodm.wordguesser.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static String WORDGUESSER_DATABASE_NAME = "wordguesser_db";
    private static int WORDGUESSER_DATABASE_VERSION = 2;
    public static final String USERS_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_USUARIO = "usuario";
    public static final String USER_COLUMN_CONTRASEÑA = "passwd";
    public static final String USER_COLUMN_NOMBRE = "nombre";
    public static final String USER_COLUMN_APELLIDOS = "apellidos";

    public static final String GAMES_TABLE_NAME = "users";
    public static final String GAME_COLUMN_ID = "_id";
    public static final String GAME_COLUMN_IDIOMA = "idioma";
    public static final String GAME_COLUMN_MODO = "modo";
    public static final String GAME_COLUMN_DIFICULTAD = "dificultad";
    public static final String GAME_COLUMN_RESULTADO = "resultado";
    public static final String GAME_COLUMN_JUGADOR = "jugador";


    public DBManager(@Nullable Context context) {
        super(context, WORDGUESSER_DATABASE_NAME, null, WORDGUESSER_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + USERS_TABLE_NAME + "(" +
                    USER_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    USER_COLUMN_USUARIO + " TEXT NOT NULL," +
                    USER_COLUMN_CONTRASEÑA + " TEXT NOT NULL," +
                    USER_COLUMN_NOMBRE + " TEXT NOT NULL," +
                    USER_COLUMN_APELLIDOS + " TEXT NOT NULL" +
                    ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + GAMES_TABLE_NAME + "(" +
                    GAME_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    GAME_COLUMN_IDIOMA + " TEXT NOT NULL," +
                    GAME_COLUMN_MODO + " TEXT NOT NULL," +
                    GAME_COLUMN_DIFICULTAD + " TEXT NOT NULL," +
                    GAME_COLUMN_RESULTADO + " INTEGER NOT NULL," +
                    GAME_COLUMN_JUGADOR + " INTEGER NOT NULL" +
                    ")");

            //Como definir una foreign key o como solucionarlo con SQLite


            db.setTransactionSuccessful();

        } catch (SQLException exception) {
            Log.e(DBManager.class.getName(), "onCreate", exception);
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(DBManager.class.getSimpleName(), "onUpgrade call!");
    }
}

