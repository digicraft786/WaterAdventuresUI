package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digicraft.wateradventures.R;

/**
 * Created by mac on 22/06/2018.
 */

public class MassiveCancelationAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public MassiveCancelationAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = inflater.inflate(R.layout.massive_cancelation_item_layout, null);
        } else {
            convertView = view;
        }


        return convertView;
    }
}
