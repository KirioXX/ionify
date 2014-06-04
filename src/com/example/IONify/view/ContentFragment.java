package com.example.IONify.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.IONify.R;

/**
 * Created by BEN on 27.05.2014.
 */
public class ContentFragment extends Fragment {

    public ContentFragment(){}

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        return rootView;

    }
}
