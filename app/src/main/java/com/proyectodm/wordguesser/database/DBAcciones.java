package com.proyectodm.wordguesser.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAcciones {

    private DBManager dbManager;

    public DBAcciones(DBManager dbManager){
        this.dbManager = dbManager;
    }

    public static Task readTask(Cursor cursor){
        Task toret = null;
        if (cursor!=null){
            try {
                toret = new Task();
                toret.setId(cursor.getInt(cursor.getColumnIndex(DBManager.TASK_COLUMN_ID)));
                toret.setName(cursor.getString(cursor.getColumnIndex(DBManager.TASK_COLUMN_NAME)));
                toret.setDone(cursor.getInt(cursor.getColumnIndex(DBManager.TASK_COLUMN_DONE))==1);
            }catch (Exception e){
                Log.e(DBAcciones.class.getName(),"readTask" ,e);
            }
        }
        return toret;
    }

    public void toggleDone() {
        SQLiteDatabase writableDatabase = dbManager.getWritableDatabase();
        try{
            writableDatabase.beginTransaction();
            writableDatabase.execSQL(
                    "UPDATE "
                            + DBManager.TASKS_TABLE_NAME
                            + " SET " + DBManager.TASK_COLUMN_DONE + "=1 "
                            + "WHERE 1=1");
            writableDatabase.setTransactionSuccessful();
        }catch(SQLException exception){
            Log.e(DBAcciones.class.getName(), "toggleDone", exception);
        }finally {
            writableDatabase.endTransaction();
        }
    }

    public void deleteDone() {
        SQLiteDatabase writableDatabase = dbManager.getWritableDatabase();
        try{
            writableDatabase.beginTransaction();
            writableDatabase.execSQL(
                    "DELETE FROM "
                            + DBManager.TASKS_TABLE_NAME
                            + " WHERE "
                            + DBManager.TASK_COLUMN_DONE +"=1"
                    , new Object[]{});
            writableDatabase.setTransactionSuccessful();
        }catch(SQLException exception){
            Log.e(DBAcciones.class.getName(), "deleteDone", exception);
        }finally {
            writableDatabase.endTransaction();
        }
    }

    public void createTask(Task task) {
        SQLiteDatabase writableDatabase = dbManager.getWritableDatabase();
        try{
            writableDatabase.beginTransaction();
            writableDatabase.execSQL(
                    "INSERT INTO " +DBManager.TASKS_TABLE_NAME +
                            "(" +
                            DBManager.TASK_COLUMN_NAME +
                            ","+
                            DBManager.TASK_COLUMN_DONE+
                            ") VALUES (?,?)"
                    , new Object[]{task.getName(), task.isDone()});
            writableDatabase.setTransactionSuccessful();
        }catch(SQLException exception){
            Log.e(DBAcciones.class.getName(), "createTask", exception);
        }finally {
            writableDatabase.endTransaction();
        }
    }

    public void removeTask(Task task) {
        SQLiteDatabase writableDatabase = dbManager.getWritableDatabase();
        try{
            writableDatabase.beginTransaction();
            writableDatabase.execSQL(
                    "DELETE FROM "
                            + DBManager.TASKS_TABLE_NAME
                            + " WHERE "
                            + DBManager.TASK_COLUMN_ID +"=?"
                    , new Object[]{task.getId()});
            writableDatabase.setTransactionSuccessful();
        }catch(SQLException exception){
            Log.e(DBAcciones.class.getName(), "removeTask", exception);
        }finally {
            writableDatabase.endTransaction();
        }
    }

    public void updateTask(Task task) {
        SQLiteDatabase writableDatabase = dbManager.getWritableDatabase();
        try{
            writableDatabase.beginTransaction();
            writableDatabase.execSQL(
                    "UPDATE "
                            + DBManager.TASKS_TABLE_NAME
                            + " SET "
                            + DBManager.TASK_COLUMN_NAME + "=?, "
                            + DBManager.TASK_COLUMN_DONE + "=? "
                            + "WHERE "+DBManager.TASK_COLUMN_ID +"=?",
                    new Object[]{task.getName(), task.isDone()?1:0, task.getId()});

            writableDatabase.setTransactionSuccessful();
        }catch(SQLException exception){
            Log.e(DBAcciones.class.getName(), "updateTask", exception);
        }finally {
            writableDatabase.endTransaction();
        }
    }

    public Cursor getTasks(){
        Cursor toret = null;

        toret = dbManager.getReadableDatabase().rawQuery("SELECT * FROM "+DBManager.TASKS_TABLE_NAME,
                null);

        return toret;
    }

    public Task getTaskById(Integer id) {
        Task toret = null;
        if (id!=null){
            Cursor cursor =
                    dbManager.getReadableDatabase().rawQuery("SELECT * FROM " + DBManager.TASKS_TABLE_NAME
                                    + " WHERE "
                                    + DBManager.TASK_COLUMN_ID + " = ?",
                            new String[]{id+""});

            if (cursor.moveToFirst()){
                toret = readTask(cursor);
            }
            cursor.close();
        }

        return toret;

    }
}
