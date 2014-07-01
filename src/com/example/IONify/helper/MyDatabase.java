package com.example.IONify.helper;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ben on 24.06.14.
 */
public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "IONifydb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String MAIN_TABLE="elements";
    private static final String[] MENU_COLUMS = {"_id","name"};
    private static final String[] ALL_COLUMS = {"_id","name","symbol","elektronenkonfiguration","atommasse","schmelzpunkt","siedepunkt","dichte","schmelzwaerme","spezifischeWaerme"};

    public String getDatabaseName(){
        return DATABASE_NAME;
    }

    public String getMainTable(){
        return MAIN_TABLE;
    }

    public String[] getMenuColums(){return MENU_COLUMS;}

    public String[] getAllColums(){return ALL_COLUMS;}

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
