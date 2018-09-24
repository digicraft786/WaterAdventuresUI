package com.digicraft.wateradventures.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.digicraft.wateradventures.R;

/**
 * Created by mac on 22/06/2018.
 */

public class ExtraOrdinaryAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    public ExtraOrdinaryAdapter(Context context)
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
            convertView = inflater.inflate(R.layout.extra_ordinary_item_layout, null);
        } else {
            convertView = view;
        }

       TextView txt = convertView.findViewById(R.id.extra_name);
       txt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                  makeDialog(v.getContext());
           }
       });

        return convertView;
    }


    public void makeDialog(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.dialog_extra_layout, null, false);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }

                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }

}
