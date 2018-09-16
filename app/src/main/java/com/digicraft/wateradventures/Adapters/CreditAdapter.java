package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.digicraft.wateradventures.R;

/**
 * Created by mac on 22/06/2018.
 */

public class CreditAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public CreditAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView = inflater.inflate(R.layout.store_credit_list_item_layout, null);
        } else {
            convertView = view;
        }


        ImageButton editBtn = convertView.findViewById(R.id.editBtnStore);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Edit Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    

}
