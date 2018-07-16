package com.digicraft.wateradventures.Sales;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.digicraft.wateradventures.R;

public class SummaryActivity extends AppCompatActivity {

    SpannableStringBuilder ssBuilder;
    TextView termsAndConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        termsAndConditions = findViewById(R.id.terms_and_conditions);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
