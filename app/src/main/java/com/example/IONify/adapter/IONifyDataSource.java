package com.example.IONify.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.IONify.helper.MyDatabase;
import com.example.IONify.model.Element;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 25.06.14.
 */
public class IONifyDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MyDatabase dbHelper;

    public IONifyDataSource(Context context) {
        dbHelper = new MyDatabase(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public List<String> getMenuTitleList() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = database.query(dbHelper.getMainTable(),dbHelper.getMenuColums(), null, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String safe = cursor.getString(cursor.getColumnIndex("_id"))+". "+cursor.getString(cursor.getColumnIndex("name"));
            list.add(safe);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Element getElementContent(int element_id) {
        Element element = new Element();
        String _id = Integer.toString(element_id);

        Cursor cursor = database.query(dbHelper.getMainTable(), dbHelper.getAllColums(), "_id=\'"+_id+"\'", null, null, null, null);
        cursor.moveToFirst();
        element = cursorToElement(cursor);
        cursor.close();
        return element;
    }

    private Element cursorToElement(Cursor cursor) {
        Element celement = new Element();
        String[] ColumIndex = dbHelper.getAllColums();
        celement.setId(cursor.getInt(cursor.getColumnIndex(ColumIndex[0])));
        celement.setName(cursor.getString( cursor.getColumnIndex(ColumIndex[1]) ));
        celement.setSymbol(cursor.getString( cursor.getColumnIndex(ColumIndex[2]) ));
        celement.setElektronenkonfiguration(cursor.getString(cursor.getColumnIndex(ColumIndex[3])));
        celement.setAtommasse(cursor.getDouble(cursor.getColumnIndex(ColumIndex[4])));
        celement.setSchmelzpunkt(cursor.getDouble(cursor.getColumnIndex(ColumIndex[5])));
        celement.setSiedepunkt(cursor.getDouble(cursor.getColumnIndex(ColumIndex[6])));
        celement.setDichte(cursor.getString(cursor.getColumnIndex(ColumIndex[7])));
        celement.setSchmelzwärme(cursor.getString(cursor.getColumnIndex(ColumIndex[8])));
        celement.setSpezifischeWärme(cursor.getString(cursor.getColumnIndex(ColumIndex[9])));
        return celement;
    }

}
