package com.example.eironeia.parking;

/**
 * Created by A on 01/05/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;


public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Coches.db";
    public static final String TABLA_COCHES = "Coches";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MATRICULA = "matricula";
    public static final String COLUMN_DIA = "dia";
    public static final String COLUMN_MES = "mes";
    public static final String COLUMN_ANY = "any";
    public static final String COLUMN_HORA = "hora";
    public static final String COLUMN_MINUT = "minut";
    public static final String COLUMN_DIASORTIDA = "diaSortida";
    public static final String COLUMN_MESSORTIDA = "mesSortida";
    public static final String COLUMN_ANYSORTIDA = "anySortida";
    public static final String COLUMN_HORASORTIDA = "horaSortida";
    public static final String COLUMN_MINUTSORTIDA = "minutSortida";



    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLA_COCHES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MATRICULA + " TEXT, " +
                COLUMN_DIA + " INTEGER, " +
                COLUMN_MES + " INTEGER, " +
                COLUMN_ANY + " INTEGER, " +
                COLUMN_HORA + " INTEGER, " +
                COLUMN_MINUT + " INTEGER, " +
                COLUMN_DIASORTIDA + " INTEGER, " +
                COLUMN_MESSORTIDA + " INTEGER, " +
                COLUMN_ANYSORTIDA + " INTEGER, " +
                COLUMN_HORASORTIDA + " INTEGER, " +
                COLUMN_MINUTSORTIDA + " INTEGER " +
                ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_COCHES);
        onCreate(db);
    }

    //Añade un nuevo Row a la Base de Datos

    public void addCoche(Coche coche) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_MATRICULA, coche.get_matricula());
        values.put(COLUMN_DIA, coche.get_dia());
        values.put(COLUMN_MES, coche.get_mes());
        values.put(COLUMN_ANY, coche.get_any());
        values.put(COLUMN_HORA, coche.get_hora());
        values.put(COLUMN_MINUT, coche.get_minut());
        values.put(COLUMN_DIASORTIDA, coche.get_diaSortida());
        values.put(COLUMN_MESSORTIDA, coche.get_mesSortida());
        values.put(COLUMN_ANYSORTIDA, coche.get_anySortida());
        values.put(COLUMN_HORASORTIDA, coche.get_horaSortida());
        values.put(COLUMN_MINUTSORTIDA, coche.get_minutSortida());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_COCHES, null, values);
        db.close();

    }

    // Borrar una persona de la Base de Datos

    public void borrarCoche(String coche_matricula){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLA_COCHES + " WHERE " + COLUMN_MATRICULA + " = '" + coche_matricula + "';");
        db.close();
    }

    public void deleteCoche(String coche_matricula, int dia, int mes, int any, int hora, int min){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLA_COCHES + " WHERE "
                + COLUMN_MATRICULA + " = '" + coche_matricula + "' AND "
                + COLUMN_DIA + " = '" + dia + "' AND "
                + COLUMN_MES + " = '" + mes + "' AND "
                + COLUMN_ANY + " = '" + any + "' AND "
                + COLUMN_HORA + " = '" + hora+ "' AND "
                + COLUMN_MINUT + " = '" + min + "' " +
                 ";");
        db.close();
    }


    //listar por id

    public Cursor cocheByMat(String mat){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        String query = "SELECT * FROM " + TABLA_COCHES + " WHERE " + COLUMN_MATRICULA + " = '" + mat + "';";
        Cursor cursor = db.rawQuery(query, null);


        return cursor;
    }

    public int size(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT _id FROM "+TABLA_COCHES+ " WHERE "+ COLUMN_HORASORTIDA +" = " + -1 +" ORDER BY "+ COLUMN_ID, null).getCount();
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ TABLA_COCHES);
        db.close();
    }

    //listar a todas las personas
    public Cursor listarCoche(){
        SQLiteDatabase db = getReadableDatabase();
        //+ " ORDER BY " + COLUMN_ID
        String query = ("SELECT * FROM " + TABLA_COCHES + " WHERE "+ COLUMN_HORASORTIDA +" = " + -1 +" ORDER BY "+ COLUMN_ID + ";");
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listarCocheOut(){
        SQLiteDatabase db = getReadableDatabase();
        //+ " ORDER BY " + COLUMN_ID
        String query = ("SELECT * FROM " + TABLA_COCHES + " WHERE "+ COLUMN_HORASORTIDA +" <> " + -1 +" ORDER BY "+ COLUMN_ID + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listarRecaptacioDia(int d, int m, int a, int h) {
        SQLiteDatabase db = getReadableDatabase();
        //+ " ORDER BY " + COLUMN_ID
        String query = ("SELECT * FROM " + TABLA_COCHES + " WHERE "
                + COLUMN_DIASORTIDA + " = '" + d + "' AND "
                + COLUMN_MESSORTIDA + " = '" + m + "' AND "
                + COLUMN_ANYSORTIDA + " = '" + a + "' AND "
                + COLUMN_HORASORTIDA + " <= '" + h
                + "' ORDER BY " + COLUMN_ID + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public Cursor listarRecaptacioDates(int m, int a, int m2, int a2) {
        SQLiteDatabase db = getReadableDatabase();
        //+ " ORDER BY " + COLUMN_ID
        String query = ("SELECT * FROM " + TABLA_COCHES + " WHERE "
                + COLUMN_MESSORTIDA + " >= '" + m + "' AND "
                + COLUMN_MESSORTIDA + " <= '" + m2 + "' AND "
                + COLUMN_ANYSORTIDA + " >= '" + a + "' AND "
                + COLUMN_ANYSORTIDA + " <= '" + a2 + "' "
                + " ORDER BY " + COLUMN_ID + ";");
        Cursor c = db.rawQuery(query, null);

        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }
}




