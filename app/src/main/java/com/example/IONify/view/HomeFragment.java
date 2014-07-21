package com.example.IONify.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.IONify.IONifyActivity;
import com.example.IONify.R;

/**
 * Created by BEN on 03.06.2014.
 */
public class HomeFragment extends Fragment {
    public HomeFragment(){
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_fragment, container, false);

        Button button = (Button) rootView.findViewById(R.id.button_menu);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((IONifyActivity)getActivity()).openDrawer();
            }
        });
        return rootView;

    }
}
