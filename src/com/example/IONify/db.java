/**
 * Created by SchmidtMa on 23.05.2014.
 */

package com.example.IONify;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.database.SQLException;


import java.sql.*;


public class db {
    //Columns
    public static final String ORDNZ = "ordnungszahl";
    public static final String NAME = "name";
    public static final String SYMBOL = "symbol";
    public static final String ELEKK = "elektronenkonfiguration";
    public static final String ATOMM = "atommasse";
    public static final String SCHMP = "schmelzpunkt";
    public static final String SIEDP = "siedepunkt";
    public static final String DICHTE = "dichte";
    public static final String SCHMW = "schmelzwärme";
    public static final String SPEZW = "spezifischeWärme";
    //DB, Table,version
    public static final String DATABASE_TABLE_NAME = "Elements";
    public static final String DATA_BASE_NAME = "DataBase";
    public static final int DATABASE_VERSION = 1;
    //Create Table string
    public static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_NAME + "(" +
                ORDNZ + " INTEGER PRIMARY KEY ASC , " +
                NAME + " TEXT , " +
                SYMBOL + " TEXT , )" +
                ELEKK + " TEXT , " +
                ATOMM + " REAL , " +
                SCHMP + " REAL , " +
                SIEDP + " REAL , " +
                DICHTE + " REAL, " +
                SCHMW + " REAL , " +
                SPEZW + " REAL );";

    //embedded Datenbank
    public static SQLiteDatabase m_db;
    private DataBaseHelper m_dbHelper;
    private final Context m_context;

    public db(Context ctx){
        this.m_context = ctx;
        m_dbHelper = new DataBaseHelper(m_context);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper{

        public DataBaseHelper(Context context){
            super(context,DATA_BASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(TABLE_CREATE);/*TABLE_NAME*/
                System.out.println("table " + DATABASE_TABLE_NAME + " created");
            }
            catch(SQLException e){
                System.out.println("table " + DATABASE_TABLE_NAME + " not created");
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
            newVersion = oldVersion + 1;
            onCreate(db);

            System.out.println("updated");
        }
    }
    //opens DB
    public db open(){
        m_db = m_dbHelper.getWritableDatabase();
        return this;
    }
    //closes DB
    public void close(){
        if(m_db != null) m_db.close();
        if(m_dbHelper != null) m_dbHelper.close();
    }
    public void query(){

    }

    public long insertData(
            int ordnz, String name, String symbol, String elekk, double atomm,
            double schmp, double siedp, double dichte, double schmw, double spezw){
        ContentValues content = new ContentValues();
        content.put(ORDNZ, ordnz);
        content.put(NAME, name);
        content.put(SYMBOL, symbol);
        content.put(ELEKK, elekk);
        content.put(ATOMM, atomm);
        content.put(SCHMP, schmp);
        content.put(SIEDP, siedp);
        content.put(DICHTE, dichte);
        content.put(SCHMW, schmw);
        content.put(SPEZW, spezw);

        return m_db.insertOrThrow(DATABASE_TABLE_NAME, null, content);
    }

    public Cursor returnData(){
        return m_db.query(DATABASE_TABLE_NAME, new String[]{NAME, SYMBOL}, null, null, null, null, null);

    }

    public int getDBVersion(){
        return DATABASE_VERSION;
    }
}
