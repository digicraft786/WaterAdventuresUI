package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digicraft.wateradventures.R;

/**
 * Created by mac on 29/05/2018.
 */

public class QrEmailAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public QrEmailAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 10;
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

        View convertView;

        if (view == null) {
            convertView = inflater.inflate(R.layout.qr_email_list_item, null);
        } else {
            convertView = view;
        }

        return convertView;
    }
}
