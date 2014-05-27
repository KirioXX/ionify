package com.example.IONify; /**
 * Created by BEN on 27.05.2014.
 */
         import android.app.Fragment;

        import android.os.Bundle;

        import android.view.LayoutInflater;

        import android.view.View;

        import android.view.ViewGroup;

         import com.example.IONify.R;

public class SunFragment extends Fragment {

    public SunFragment(){}

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sun, container, false);

        return rootView;

    }

}
