package com.example.kaiwang.soapbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaiwang on 25.8.2015.
 */
public class ListProfileAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ListProfileAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView NAME, ID;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        TextView textView;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.profile_row, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.profile_user_name);
            layoutHandler.ID = (TextView)row.findViewById(R.id.profile_user_id);
            row.setTag(layoutHandler);
        }else{
            layoutHandler = (LayoutHandler)row.getTag();

        }
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getName()+ ": ");
        layoutHandler.ID.setText(dataProvider.getId());

        return row;
    }
}
