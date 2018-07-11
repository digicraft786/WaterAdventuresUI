package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.Sales.SportDetailActivity;
import com.digicraft.wateradventures.Sales.SummaryActivity;

/**
 * Created by mac on 29/05/2018.
 */

public class SearchAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    boolean flag = false;

    public SearchAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public SearchAdapter(Context context, boolean flag) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.flag = flag;
    }

    @Override
    public int getCount() {
        return 6;
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
            convertView = inflater.inflate(R.layout.search_item_layout, null);
        } else {
            convertView = view;
        }

        ImageView imageView = convertView.findViewById(R.id.thumbnail);
        TextView someTextView = (TextView) convertView.findViewById(R.id.cross_word);
        someTextView.setText("150USD");
        someTextView.setPaintFlags(someTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        Button bookNow = convertView.findViewById(R.id.bookNow);
        if (flag) {
            bookNow.setVisibility(View.VISIBLE);
        }

         imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 context.startActivity(new Intent(context , SportDetailActivity.class));
             }
         });

        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SummaryActivity.class));
            }
        });
        return convertView;
    }
}
