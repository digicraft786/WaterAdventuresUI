package com.digicraft.wateradventures.ModifyReservation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.TimmingScheduleAdapter;
import com.digicraft.wateradventures.Calender.CalenderFragment;
import com.digicraft.wateradventures.Network.Url;
import com.digicraft.wateradventures.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResheduleReservationActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reshedule_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.reshedule_timming_lv);
        TimmingScheduleAdapter adapter = new TimmingScheduleAdapter(this);
        listView.setAdapter(adapter);


    }



}
