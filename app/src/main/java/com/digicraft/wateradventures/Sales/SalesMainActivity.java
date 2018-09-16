package com.digicraft.wateradventures.Sales;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.SearchSpinnerAdapter;
import com.digicraft.wateradventures.Calender.CalenderActivity;
import com.digicraft.wateradventures.Fragments.SalesOpener;
import com.digicraft.wateradventures.Fragments.SearchFragment;
import com.digicraft.wateradventures.Fragments.SearchResultFragment;
import com.digicraft.wateradventures.QR.ScanQrActivity;
import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.StoreCredit.StoreCreditActivity;
import com.digicraft.wateradventures.Util;

import java.util.ArrayList;

public class SalesMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SalesOpener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        int pos = getIntent().getIntExtra("screen", 0);
        if (pos == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new SearchFragment()).commit();
        } else if (pos == 1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new SearchResultFragment()).commit();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationViewRight = findViewById(R.id.nav_right_view);

        View headerLayout =
                navigationViewRight.inflateHeaderView(R.layout.nav_header_sales_main);

        final EditText location = headerLayout.findViewById(R.id.location_et);
        EditText waterActivity = headerLayout.findViewById(R.id.waterActivity_et);
        EditText duration = headerLayout.findViewById(R.id.duration_et);

        waterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDialog(v);
            }
        });

        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDialog(v);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext() , "Clicked" , Toast.LENGTH_SHORT).show();
                 locationDialog(v);
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sales_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sale) {
            // Handle the camera action
            startActivity(new Intent(this, SalesMainActivity.class));
        } else if (id == R.id.nav_qr) {
            if (Util.checkCameraPermission(this)) {
                startActivity(new Intent(this, ScanQrActivity.class));
            }
        } else if (id == R.id.nav_calendar) {
            startActivity(new Intent(this, CalenderActivity.class));
        }else if (id == R.id.nav_store_credit) {
            startActivity(new Intent(this, StoreCreditActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void goToResultScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, new SearchResultFragment()).commit();
    }


    public void locationDialog(View view)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout


        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.searchable_spinner_dialog, null, false);

        builder.setView(formElementsView);
        builder.setTitle("Pick a Location");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // Add action buttons

        ListView listView = formElementsView.findViewById(R.id.searchable_spinner_listview);
        ArrayList<String> areas = new ArrayList<>();
        areas.add("Grand Velas Rivera Maya");
        areas.add("Fiesta Americana");
        areas.add("Grand Velas Rivera Maya");
        SearchSpinnerAdapter adapter = new SearchSpinnerAdapter(this ,areas );
        listView.setAdapter(adapter);


        builder.create();
        builder.show();

    }
}
