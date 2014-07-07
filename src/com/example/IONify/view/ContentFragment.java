package com.example.IONify.view;


import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.IONify.ParsedJson;
import com.example.IONify.R;
import com.example.IONify.adapter.FragArrayAdapter;
import com.example.IONify.model.Element;
import com.example.IONify.model.FrakModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.jar.Attributes;

/**
 * Created by BEN on 27.05.2014.
 */
public class ContentFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {
    private FragArrayAdapter AllgAdapter;
    private FragArrayAdapter PhyAdapter;
    private FragArrayAdapter LadAdapter;
    private Element element;
    private String[] Allgemein = {"Name","Symbol","Id","Elektronenkonfiguration","Atommasse"};
    private String[] Physikalisch = {"Schmelzpunkt","Siedepunkt","Dichte","Schmelzwärme","SpezifischeWärme"};
    private String[] Ladungszustand = {"Elektronenkonfiguration","Ionisationsenergien","Ionisationsenergien der außeren Schale","Energiepotential"};
    private TextView textProgress;
    private ListView FragLadList;
    public View rootView;

    public ContentFragment(Element element){
        this.element =element;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_content, container, false);

        //set ListView for Allgemein
        ListView FragAllgList = (ListView) rootView.findViewById(R.id.list_allgemein);
        setAllgAdapter(rootView);
        FragAllgList.setAdapter(AllgAdapter);

        //set ListView for Physikalisch
        ListView FragPhyList = (ListView) rootView.findViewById(R.id.list_physikalisch);
        setPhyAdapter(rootView);
        FragPhyList.setAdapter(PhyAdapter);
        if(element.getId()<59){
            //set SeekBar max
            SeekBar FragSeekbar = (SeekBar) rootView.findViewById(R.id.frag_seekBar);
            FragSeekbar.setMax(element.getId());
            FragSeekbar.setOnSeekBarChangeListener(this);

            //set ListView Ladungszustand
            ParseJSON();
            FragLadList = (ListView) rootView.findViewById(R.id.list_ladungsstand);
            setLadAdapter(rootView,0);
            FragLadList.setAdapter(LadAdapter);
            // make text label for progress value
            textProgress = (TextView) rootView.findViewById(R.id.textViewProgress);
        }
        return rootView;

    }

  private void setAllgAdapter(View rootView){
      ArrayList<FrakModel> AllgItems = new ArrayList<FrakModel>();
      // adding nav drawer items to array
      for (String temp : Allgemein) {
          String titleVal = temp+":";
          String contentVal = element.getWhitUnit(temp);
          AllgItems.add(new FrakModel(titleVal,contentVal));
      }
      AllgAdapter = new FragArrayAdapter(rootView.getContext(),AllgItems);
  }

    private void setPhyAdapter(View rootView){
        ArrayList<FrakModel> PhyItems = new ArrayList<FrakModel>();
        // adding nav drawer items to array
        for (String temp : Physikalisch) {
            String titleVal = temp+":";
            String contentVal = element.getWhitUnit(temp);
            PhyItems.add(new FrakModel(titleVal,contentVal));
        }
        PhyAdapter = new FragArrayAdapter(rootView.getContext(),PhyItems);
    }
    private void setLadAdapter(View rootView,int prog){
        ArrayList<FrakModel> LadItems = new ArrayList<FrakModel>();
        //adding nav drawer items to array
        for (String temp : Ladungszustand){
            String titleVal = temp+":";
            String contentVal = "";
            /*if (temp.equals("Elektronenkonfiguration")) {
                contentVal = element.getLadElektronenkonfiguration(prog);
            }*/
            if (temp.equals("Ionisationsenergien")){
                contentVal = element.getEnergiepotential(prog);
            }
            if (temp.equals("Energiepotential")){
                contentVal = element.getEnergiepotential((prog));
            }

            LadItems.add(new FrakModel(titleVal,contentVal));
        }
        LadAdapter = new FragArrayAdapter(rootView.getContext(),LadItems);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // change progress text label with current seekbar value


        textProgress.setText("Ladungszustand: " + progress);
        setLadAdapter(rootView, progress);
        FragLadList.setAdapter(LadAdapter);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void ParseJSON(){
        int elem = element.getId();
        //reading text file
        Context context = rootView.getContext();
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open("json/" + elem + "data.json")));
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); //stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String json = sb.toString();

        JSONObject jObject = null;
        try {
            jObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //get Eion Object
        JSONObject energiepotentiale = null;
        JSONObject ionisationsenergien = null;
        JSONObject elektronenkonfigurationen = null;
        try {
            //parse objects epot eion conf
            energiepotentiale = jObject.getJSONObject("Epot");
            ionisationsenergien = jObject.getJSONObject("Eion");
            elektronenkonfigurationen = jObject.getJSONObject("Conf");

            //get potential values
            String epot = null;
            String ion = null;
            JSONArray ionArray;
            List<String> potstring = new ArrayList<String>();
            List<String> ionstring = new ArrayList<String>();

            try{
                for(int i=1; i<=energiepotentiale.length();i++) {
                    epot = energiepotentiale.getString(""+i);
                    potstring.add(epot+" eV");
                }
            }catch(JSONException e){
                e.printStackTrace();
            }


            try {
                for (int i = 1; i < ionisationsenergien.length(); i++) {
                    ionArray = ionisationsenergien.getJSONArray("" + i);
                    ion = ionArray.toString();
                    ionstring.add(ion);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            element.setEnergiepotential(potstring);
            element.setIonisationsenergien(ionstring);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
