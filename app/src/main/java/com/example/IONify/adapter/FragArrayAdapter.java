package com.example.IONify.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.IONify.R;
import com.example.IONify.model.FrakModel;
import com.example.IONify.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 26.06.14.
 */
public class FragArrayAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<FrakModel> FrakItems;

    public FragArrayAdapter(Context context, ArrayList<FrakModel> FrakItems){

        this.context = context;

        this.FrakItems = FrakItems;

    }

    @Override

    public int getCount() {

        return FrakItems.size();

    }

    @Override

    public Object getItem(int position) {

        return FrakItems.get(position);

    }

    @Override

    public long getItemId(int position) {

        return position;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.fragment_list_item, null);

        }


        TextView txtTitle = (TextView) convertView.findViewById(R.id.tv_title);

        TextView txtContent = (TextView) convertView.findViewById(R.id.tv_content);

        txtTitle.setText(FrakItems.get(position).getTitle());

        txtContent.setText(FrakItems.get(position).getContent());

        return convertView;

    }
}
