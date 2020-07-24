package com.example.appliccation01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater inflater;
    String[] items;
    String[] description;

    public ItemAdapter(Context c, String[] i, String[] d){
        items = i;
        description = d;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.my_listview_detail, null);
        TextView countryTxtView = (TextView) v.findViewById(R.id.CountryTextView);
        TextView descriptionTatView = (TextView) v.findViewById(R.id.descriptionTextView);

        String name = items[position];
        String description = this.description[position];

        countryTxtView.setText(name);
        descriptionTatView.setText(description);

        return v;
    }
}
