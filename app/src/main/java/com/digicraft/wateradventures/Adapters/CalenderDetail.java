package com.digicraft.wateradventures.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.digicraft.wateradventures.Models.SaleModel;
import com.digicraft.wateradventures.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mac on 22/06/2018.
 */

public class CalenderDetail extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<SaleModel> saleModels;

    public  CalenderDetail(Context context , ArrayList<SaleModel> saleModels)
    {
        this.context = context;
        this.saleModels = saleModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return saleModels.size();
    }

    @Override
    public Object getItem(int position) {
        return saleModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final View convertView;
        TextView detailTxt;
        ImageView dot;
        TextView startTime , duration;

        if (view == null) {
            convertView = inflater.inflate(R.layout.calender_detail_item, null);
        } else {
            convertView = view;
        }

        SaleModel obj = saleModels.get(position);

        detailTxt = convertView.findViewById(R.id.activityName);
        dot = convertView.findViewById(R.id.dot);
        startTime = convertView.findViewById(R.id.startTime);
        duration = convertView.findViewById(R.id.duration);

       //2018-08-07 14:00:00.0



        detailTxt.setText(obj.activityName+" , "+obj.customerFirstName+" "+obj.customerLastName);
        dot.setBackgroundColor(Color.parseColor(obj.hexColor));
        startTime.setText(obj.activityTimeStart.substring(11 , 16));


        try {
           double difference = getTimeDuration(obj.activityTimeStart.substring(11, 19) , obj.activityTimeEnd.substring(11, 19));
           String displayTxt = "";
           if (difference / 60 < 1)
           {
               int mins = (int) difference;
               displayTxt = mins+" MINS";
           }else
           {
                double hrs = difference / 60;
                displayTxt = hrs+" HRS";
           }

           duration.setText(displayTxt);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public double getTimeDuration(String time1 , String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();
        return difference/60000;
    }

}
