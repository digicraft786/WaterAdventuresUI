package com.digicraft.wateradventures;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.digicraft.wateradventures.Calender.CalenderActivity;
import com.digicraft.wateradventures.Misc.ForgotPasswordActivity;
import com.digicraft.wateradventures.Misc.LoginActivity;
import com.digicraft.wateradventures.QR.ScanQrActivity;
import com.digicraft.wateradventures.Sales.ReservationConfirmationActivity;
import com.digicraft.wateradventures.Sales.SalesMainActivity;
import com.digicraft.wateradventures.Sales.SummaryActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickMenu(View view) {
        int btnId = view.getId();

        if (btnId == R.id.btn1) {
            startActivity(new Intent(this, SalesMainActivity.class));
        } else if (btnId == R.id.btn2) {
            startActivity(new Intent(this, ScanQrActivity.class));
        } else if (btnId == R.id.btn3) {
            startActivity(new Intent(this, CalenderActivity.class));
        }else if (btnId == R.id.btn4)
        {
            makeDialog(view);
        }


// else if (btnId == R.id.btn4) {
//            startActivity(new Intent(this, SummaryActivity.class));
//        } else if (btnId == R.id.btn5) {
//            startActivity(new Intent(this, LoginActivity.class));
//        } else if (btnId == R.id.btn6) {
//            startActivity(new Intent(this, ForgotPasswordActivity.class));
//        } else if (btnId == R.id.btn7) {
//            startActivity(new Intent(this, ReservationConfirmationActivity.class));
//        } else if (btnId == R.id.btn8) {
//            startActivity(new Intent(this, ScanQrActivity.class));
//        }

    }

    public void makeDialog(View view)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setView(inflater.inflate(R.layout.liablity_dialog_layout, null));
                // Add action buttons

        builder.create();
        builder.show();

    }

}
