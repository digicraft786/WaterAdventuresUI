package com.digicraft.wateradventures.ExtraOrdCancellation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.digicraft.wateradventures.Adapters.ExtraOrdinaryAdapter;
import com.digicraft.wateradventures.R;

public class ExtraOrdinaryActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_ordinary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.extra_ordinary_cond);

        ExtraOrdinaryAdapter adapter = new ExtraOrdinaryAdapter(this);
        listView.setAdapter(adapter);

        }



}
