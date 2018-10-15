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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digicraft.wateradventures.Adapters.ParticipantAdapter;
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
import java.util.HashMap;
import java.util.Map;

public class ModifyReservationActivity extends AppCompatActivity {

    ListView listView;
    Button save , cancel;
    String authToken;
    Button scheduleBtn , cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reservation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        authenticateUser();
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
                startActivity(new Intent(v.getContext() , ResheduleReservationActivity.class));
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

    public void getAllReservations() {
        JSONObject post_dict = new JSONObject();
        try {
            post_dict.put("date", "john.doe@codino.pl");
            post_dict.put("calenderView", "2018-10-05 18:30:0");
            post_dict.put("type", 2);
            post_dict.put("activity", "2");
            post_dict.put("idSale", 321);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (post_dict.length() > 0) {
            new getAllReservationsTask().execute(String.valueOf(post_dict));
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

             getAllReservations();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void getAllReservationsVolley(String token)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, Url.FIND_ALL_RESERVATION,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("date", "Alif");
                params.put("calenderView", "2018-10-05 18:30:00");
                params.put("type", "2");
                params.put("activity", "2");
                params.put("idSale", "321");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json;charset=UTF-8");
                params.put("WA-AUTH-TOKEN", authToken);
                return params;
            }
        };

        queue.add(postRequest);
    }




    class getAllReservationsTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(Url.FIND_ALL_RESERVATION);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");

                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("WA-AUTH-TOKEN" , authToken);

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


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void showToast(String msg)
    {
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();
    }

}
