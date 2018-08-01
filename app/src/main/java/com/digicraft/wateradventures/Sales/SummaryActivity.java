package com.digicraft.wateradventures.Sales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.digicraft.wateradventures.R;

public class SummaryActivity extends AppCompatActivity {

    SpannableStringBuilder ssBuilder;
    TextView termsAndConditions;
    ImageView payCashBtn, roomChargeBtn, paypalBtn;
    Button proceed_to_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        proceed_to_checkout = findViewById(R.id.proceed_to_checkout);
        payCashBtn = findViewById(R.id.payCashBtn);
        roomChargeBtn = findViewById(R.id.roomChargeBtn);
        paypalBtn = findViewById(R.id.paypalBtn);

        termsAndConditions = findViewById(R.id.terms_and_conditions);

        proceed_to_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SummaryActivity.this , ReservationConfirmationActivity.class));
            }
        });

        String text = "By tapping above and placing order, I agree that I have read and agree to be bound by\n" +
                "Water Adventure's Terms and Conditions and Privacy Statement.";
        ssBuilder = new SpannableStringBuilder(text);

        ssBuilder.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                text.indexOf("Terms and Conditions"),
                text.indexOf("Terms and Conditions") + String.valueOf("Terms and Conditions").length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        ssBuilder.setSpan(
                new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)),
                text.indexOf("Privacy Statement"),
                text.indexOf("Privacy Statement") + String.valueOf("Privacy Statement").length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        termsAndConditions.setText(ssBuilder);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void paymentGatewayHandler(View view) {
        int id = view.getId();

        paypalBtn.setBackgroundResource(R.drawable.white_border);
        payCashBtn.setBackgroundResource(R.drawable.white_border);
        roomChargeBtn.setBackgroundResource(R.drawable.white_border);

        if (id == R.id.paypalBtn) {
            paypalBtn.setBackgroundResource(R.drawable.blue_border);
        } else if (id == R.id.payCashBtn) {
            payCashBtn.setBackgroundResource(R.drawable.blue_border);
        } else if (id == R.id.roomChargeBtn) {
            roomChargeBtn.setBackgroundResource(R.drawable.blue_border);
        }
    }


    public void makeRoomChargeBillingInfo(View view)
    {

        //Inflate the Layout and get root View (formRlementView)
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.room_charge_billing_info_layout, null, false);


        //Get the References of buttons Applicable on all view (EditText , Button , Spinner .. etc)
        Button cancelBtn = formElementsView.findViewById(R.id.cancelBtn  );
        Button confirmBtn = formElementsView.findViewById(R.id.confirmBtn);

        //Show the Inflated view in the Dialogue.
        new AlertDialog.Builder(view.getContext())
                .setView(formElementsView)
                .show();

        //Handle Cancel Button Event.
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Cancel Clicked");
            }
        });

        //Handle Confirm Button Event.
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Confirm Clicked");
            }
        });

    }





    public void showToast(String msg)
    {
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();
    }

    public void makeRoomChargeParticipantInfo(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.room_charge_participant_info_layout, null));
        builder.create();
        builder.show();
    }

}
