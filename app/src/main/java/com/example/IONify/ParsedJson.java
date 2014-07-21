package com.example.IONify;

import android.content.Context;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SchmidtMa on 05.07.2014.
 */
public class ParsedJson {

    public ParsedJson(Context context, int elem) {

        String json = null;
        try {

            InputStream is = context.getAssets().open(elem + "data.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

