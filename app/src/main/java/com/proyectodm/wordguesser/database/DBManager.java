package com.proyectodm.wordguesser.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wordguesser_db";
    private static final int DATABASE_VERSION = 2;

    public static final String PLAYERS_TABLE_NAME = "players";
    public static final String PLAYER_COLUMN_ID = "_id"; //Diferencia entre definir _id como un numero o llamar al nombre unico de usuario id
    public static final String PLAYER_COLUMN_USUARIO = "usuario";
    public static final String PLAYER_COLUMN_PASSWORD = "passwd";
    public static final String PLAYER_COLUMN_NOMBRE = "nombre";
    public static final String PLAYER_COLUMN_APELLIDOS = "apellidos";
    public static final String PLAYER_COLUMN_RACHA_ACTUAL = "rachaactual";
    public static final String PLAYER_COLUMN_RACHA_MEJOR = "mejorracha";

    public static final String GAMES_TABLE_NAME = "games";
    public static final String GAME_COLUMN_ID = "_id";
    public static final String GAME_COLUMN_IDIOMA = "idioma";
    public static final String GAME_COLUMN_MODO = "modo";
    public static final String GAME_COLUMN_DIFICULTAD = "dificultad";
    public static final String GAME_COLUMN_PALABRA = "palabra";
    public static final String GAME_COLUMN_RESULTADO = "resultado";
    public static final String GAME_COLUMN_JUGADOR = "jugador";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBManager","Creando DB " + DATABASE_NAME + " v" + DATABASE_VERSION);

        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + PLAYERS_TABLE_NAME + "(" +
                    PLAYER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PLAYER_COLUMN_USUARIO + " TEXT UNIQUE," +
                    PLAYER_COLUMN_PASSWORD + " TEXT NOT NULL," +
                    PLAYER_COLUMN_NOMBRE + " TEXT NOT NULL," +
                    PLAYER_COLUMN_APELLIDOS + " TEXT NOT NULL," +
                    PLAYER_COLUMN_RACHA_ACTUAL + " INTEGER DEFAULT 0," +
                    PLAYER_COLUMN_RACHA_MEJOR + " INTEGER DEFAULT 0" +
                    ")");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + GAMES_TABLE_NAME + "(" +
                    GAME_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    GAME_COLUMN_IDIOMA + " TEXT NOT NULL," +
                    GAME_COLUMN_MODO + " TEXT NOT NULL," +
                    GAME_COLUMN_DIFICULTAD + " TEXT NOT NULL," +
                    GAME_COLUMN_PALABRA + " TEXT NOT NULL," +
                    GAME_COLUMN_RESULTADO + " INTEGER NOT NULL," +
                    GAME_COLUMN_JUGADOR + " INTEGER NOT NULL," +
                    "FOREIGN KEY(" + GAME_COLUMN_JUGADOR + ") REFERENCES " +
                    PLAYERS_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ") ON DELETE CASCADE" +
                    ")");
            db.setTransactionSuccessful();
        } catch (SQLException exception) {
            Log.e(DBManager.class.getName(), "onCreate", exception);
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager","DB: " + DATABASE_NAME + ": v" + oldVersion + " -> v" + newVersion );
        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + GAMES_TABLE_NAME);
            db.setTransactionSuccessful();
        }  catch(SQLException exception) {
            Log.e(DBManager.class.getName(), "onUpgrade", exception);
        } finally {
            db.endTransaction();
        }
        this.onCreate(db);
    }

    public boolean registerPlayer(String usuario, String passwd, String nombre, String apellidos){
        Cursor cursor = null;
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PLAYER_COLUMN_USUARIO, usuario);
        contentValues.put(PLAYER_COLUMN_PASSWORD, passwd);
        contentValues.put(PLAYER_COLUMN_NOMBRE, nombre);
        contentValues.put(PLAYER_COLUMN_APELLIDOS, apellidos);
        contentValues.put(PLAYER_COLUMN_RACHA_ACTUAL, 0);
        contentValues.put(PLAYER_COLUMN_RACHA_MEJOR, 0);

        try{
            db.beginTransaction();
            cursor = db.query(PLAYERS_TABLE_NAME,
                    null,
                    PLAYER_COLUMN_USUARIO + "=?",
                    new String[]{usuario},
                    null, null, null, null);
            if(cursor.getCount() == 0){
                //En caso de que el usuario no exista
                db.insert(PLAYERS_TABLE_NAME, null, contentValues);
                db.setTransactionSuccessful();
                toret = true;
            }
        }catch(SQLException exc){
            Log.e("DBManager.addUser", exc.getMessage());
        }finally{
            if(cursor != null){
                cursor.close();
            }
            db.endTransaction();
        }
        return toret;
    }

    public boolean checkLogin(String usuario, String passwd){
        Cursor cursor = null;
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.beginTransaction();
            cursor = db.query(PLAYERS_TABLE_NAME,
                    null,
                    PLAYER_COLUMN_USUARIO + "=? AND " + PLAYER_COLUMN_PASSWORD + "=?",
                    new String[]{usuario, passwd},
                    null, null, null, null);
            if(cursor.getCount() > 0){
                toret = true;
            }
            db.setTransactionSuccessful();
        }catch(SQLException exc){
            Log.e("DBManager.checkLogin", exc.getMessage());
        }finally{
            if(cursor != null){
                cursor.close();
            }
            db.endTransaction();
        }
        return toret;
    }

    public boolean deletePlayer(int idJugador){
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();

        try{
            db.beginTransaction();
            db.delete(PLAYERS_TABLE_NAME, PLAYER_COLUMN_ID + "=?", new String[]{String.valueOf(idJugador)});
            db.setTransactionSuccessful();
            toret = true;
        }catch(SQLException exc){
            Log.e("DBManager.deleteGame", exc.getMessage());
        }finally {
            db.endTransaction();
        }

        return toret;
    }

    public boolean addResultGame(String palabra, String modo, String dificultad, String idioma, boolean resultado, int idJugador){
        boolean toret = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(GAME_COLUMN_PALABRA, palabra);
        contentValues.put(GAME_COLUMN_MODO, modo);
        contentValues.put(GAME_COLUMN_DIFICULTAD, dificultad);
        contentValues.put(GAME_COLUMN_IDIOMA, idioma);
        contentValues.put(GAME_COLUMN_RESULTADO, (resultado ? 1 : 0));
        contentValues.put(GAME_COLUMN_JUGADOR, idJugador);

        try{
            db.beginTransaction();
            db.insert(GAMES_TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
            toret = true;
        }catch(SQLException exc){
            Log.e("DBManager.addGame", exc.getMessage());
        }finally{
            db.endTransaction();
        }
        return toret;
    }

    public Cursor getGames(int idJugador){
        return this.getReadableDatabase().query(GAMES_TABLE_NAME,
                null, GAME_COLUMN_JUGADOR + "=?", new String[]{String.valueOf(idJugador)}, null, null, null);
    }
}

