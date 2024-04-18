package com.flores.examenfinal.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosSQLite extends SQLiteOpenHelper {
    public DatosSQLite(@Nullable Context context) {

        super(context, "micaja", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE movimientos(" +
                "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pais TEXT,"+
                "poblacion int," +
                "area float," +
                "capital TEXT)");
    }

    public int movimientosInsert(DatosSQLite datosSQLite,String pais,int poblacion,float area ,String capital){
        //vamos a insertar estos datos a nuestra tabla
        SQLiteDatabase sqLiteDatabase = datosSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pais",pais);
        values.put("poblacion",poblacion);
        values.put("area",area);
        values.put("capital",capital);

        int autonumerico = (int)sqLiteDatabase.insert("movimientos",null,values);
        return autonumerico;
    }

    public Cursor movimientosSelect(DatosSQLite datosSQLite){
        SQLiteDatabase sqLiteDatabase= datosSQLite.getReadableDatabase();
        String sql="SELECT * FROM movimientos";
        return sqLiteDatabase.rawQuery(sql,null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
