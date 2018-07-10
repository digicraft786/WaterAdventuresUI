package com.digicraft.wateradventures.Misc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.digicraft.wateradventures.Misc.ForgotPasswordActivity;
import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.Sales.SalesMainActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LoginClickHandler(View view) {
        int btnId = view.getId();
        if (btnId == R.id.login_signin_btn) {
            startActivity(new Intent(this , SalesMainActivity.class));
        } else if (btnId == R.id.login_forgot_link) {
            startActivity(new Intent(this , ForgotPasswordActivity.class));
        }
    }


}
