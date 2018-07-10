package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digicraft.wateradventures.Calender.CalenderActivity;
import com.digicraft.wateradventures.R;

/**
 * Created by mac on 22/06/2018.
 */

public class CalenderAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public CalenderAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 35;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final View convertView;
        if (view == null) {
            convertView = inflater.inflate(R.layout.calender_grid_item, null);
        } else {
            convertView = view;
        }


       TextView txt = convertView.findViewById(R.id.calender_cell_txt);

        if (position <= 29) {
            txt.setText(position + 1 + "");
        }

        return convertView;
    }
}
