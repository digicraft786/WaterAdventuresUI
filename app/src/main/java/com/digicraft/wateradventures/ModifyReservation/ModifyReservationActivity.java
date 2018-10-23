package com.digicraft.wateradventures.ModifyReservation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.digicraft.wateradventures.Adapters.ParticipantAdapter;

import com.digicraft.wateradventures.Network.MyApplication;
import com.digicraft.wateradventures.Network.Url;
import com.digicraft.wateradventures.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ModifyReservationActivity extends AppCompatActivity {

    ListView listView;
    Button save, cancel;
    String authToken;
    Button scheduleBtn, cancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            getAllReservation();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView = findViewById(R.id.participant_lv);

        save = findViewById(R.id.modify_reservation_save);
        cancel = findViewById(R.id.modify_reservation_cancel);

        scheduleBtn = findViewById(R.id.modify_reschedule_select_btn);
        cancelBtn = findViewById(R.id.cancel_reschedule_select_btn);

        ParticipantAdapter adapter = new ParticipantAdapter(this);
        listView.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ResheduleReservationActivity.class));
            }
        });

    }

    public void authenticateUser() {
        JSONObject post_dict = new JSONObject();
        try {
            post_dict.put("email", "john.doe@codino.pl");
            post_dict.put("password", 1234);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (post_dict.length() > 0) {
            new getActivityTask().execute(String.valueOf(post_dict));
        }
    }


    class getActivityTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(Url.LOGIN_AS_ADMIN);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");

                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
                writer.close();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    return null;
                }

                JsonResponse = buffer.toString();
                Log.i("Response", JsonResponse);

                try {
                    return JsonResponse;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ERROR", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObj = new JSONObject(s);
                String token = jsonObj.getString("token");
                authToken = token;

                // getAllReservations();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void getAllReservation() throws JSONException {

        JSONObject postparams = new JSONObject();

        postparams.put("date", "2018-10-15 12:30:0");
        postparams.put("calendarView", "2018-10-15 13:0:0");
        postparams.put("type", "2");
        postparams.put("activity", "46");
        postparams.put("idSale", "354");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Url.FIND_ALL_RESERVATION, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        showToast(response.toString());
                        Log.d("Result", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        showToast("Request Fail");
                    }
                }) {

            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("WA-AUTH-TOKEN",
                        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZUBjb2Rpbm8ucGwiLCJleHAiOjE1Mzk3OTMxOTJ9.-6dZ9oZEwbq4pZS3z4pDP1TLqPaSJGJOFqha14630nzjjQZR44-0j3eiR6Edh0qeSd4MzuXNvIwxRFsiEhIPGA");
                return headers;
            }

        };

        MyApplication.getInstance().addToRequestQueue(jsonObjReq, "headerRequest");
    }


}
