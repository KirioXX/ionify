package com.example.IONify;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends Activity {

    db handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int ord = 1;
        String name = "wasserstoff";
        String symbol = "H";
        String elekk = "bla";
        double atomm = 1;
        double schmp = 1;
        double siedp = 1;
        double dichte = 1;
        double schmw = 1;
        double spezw = 1;

        handler = new db(getBaseContext());
        handler.open();

        long id = handler.insertData(ord,name,symbol,elekk,atomm,schmp,siedp,dichte,schmw,spezw);System.out.println("Bis hier");
        /*Toast.makeText(getBaseContext(),"Data inserted",Toast.LENGTH_LONG).show();
        handler.close();*/

    }
}
