package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.Sales.SummaryActivity;

import java.util.ArrayList;

/**
 * Created by mac on 29/05/2018.
 */

public class SearchSpinnerAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> areas;

    public SearchSpinnerAdapter(Context context , ArrayList<String> areas) {
        this.context = context;
        this.areas = areas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return areas.size();
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
            convertView = inflater.inflate(R.layout.search_spinner_list_item_layout , null);
        } else {
            convertView = view;
        }

        TextView item_tv = convertView.findViewById(R.id.text_list_layout);
        item_tv.setText(areas.get(position));


        return convertView;
    }
}
