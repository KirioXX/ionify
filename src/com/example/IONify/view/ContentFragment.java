package com.example.IONify.view;


import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.IONify.R;
import com.example.IONify.adapter.FragArrayAdapter;
import com.example.IONify.model.Element;
import com.example.IONify.model.FrakModel;

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
    private Element element;
    private String[] Allgemein = {"Name","Symbol","Id","Elektronenkonfiguration","Atommasse"};
    private String[] Physikalisch = {"Schmelzpunkt","Siedepunkt","Dichte","Schmelzwärme","SpezifischeWärme"};
    private SeekBar FragSeekbar;
    private TextView textProgress;

    public ContentFragment(Element element){
        this.element =element;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        //set ListView for Allgemein
        ListView FragAllgList = (ListView) rootView.findViewById(R.id.list_allgemein);
        setAllgAdapter(rootView);
        FragAllgList.setAdapter(AllgAdapter);

        //set ListView for Physikalisch
        ListView FragPhyList = (ListView) rootView.findViewById(R.id.list_physikalisch);
        setPhyAdapter(rootView);
        FragPhyList.setAdapter(PhyAdapter);

        //set SeekBar max
        SeekBar FragSeekbar = (SeekBar) rootView.findViewById(R.id.frag_seekBar);
        FragSeekbar.setMax(element.getId());
        FragSeekbar.setOnSeekBarChangeListener(this);

        // make text label for progress value
        textProgress = (TextView) rootView.findViewById(R.id.textViewProgress);

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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // change progress text label with current seekbar value
        textProgress.setText("Ladungszustand: "+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
