package com.digicraft.wateradventures.StoreCredit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.CreditAdapter;
import com.digicraft.wateradventures.R;

public class StoreCreditActivity extends AppCompatActivity {


    ListView creditListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_credit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        creditListView = findViewById(R.id.credit_lv);

        CreditAdapter adapter = new CreditAdapter(this);
        creditListView.setAdapter(adapter);
    }
}
