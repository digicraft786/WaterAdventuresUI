package com.digicraft.wateradventures.Sales;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.digicraft.wateradventures.Adapters.MyRecyclerViewAdapter;
import com.digicraft.wateradventures.R;
import com.github.badoualy.datepicker.DatePickerTimeline;

import java.util.ArrayList;
import java.util.Calendar;

public class SportDetailActivity extends AppCompatActivity {


    DatePickerTimeline timeline;
    TextView sliderBtn;
    RelativeLayout slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpRV();
        timeline = findViewById(R.id.timeline);
        sliderBtn = findViewById(R.id.slider_btn);
        slider = findViewById(R.id.slider);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_navigate_before_white_24dp);
        timeline.setLastVisibleDate(2020, Calendar.JULY, 19);


        sliderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             slider.setVisibility(View.VISIBLE);
            }
        });


    }

    private MyRecyclerViewAdapter adapter;
    public void setUpRV()
    {
        ArrayList<String> mTimes = new ArrayList<>();
        mTimes.add("8 am");
        mTimes.add("9:30 am");
        mTimes.add("12 pm");
        mTimes.add("1 pm");
        mTimes.add("3 pm");
        mTimes.add("5 pm");

        RecyclerView recyclerView = findViewById(R.id.slider_RV);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new MyRecyclerViewAdapter(this,  mTimes);
        recyclerView.setAdapter(adapter);
    }

}
