package com.digicraft.wateradventures.MassiveCancelation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.digicraft.wateradventures.Adapters.MassiveCancelationAdapter;
import com.digicraft.wateradventures.R;

public class MassiveCancelationActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massive_cancelation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.massive_cancelation_lv);
        MassiveCancelationAdapter adapter = new MassiveCancelationAdapter(this);
        listView.setAdapter(adapter);



        }
}
