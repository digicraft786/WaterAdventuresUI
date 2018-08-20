package com.digicraft.wateradventures.Calender;


import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.CalenderAdapter;
import com.digicraft.wateradventures.Adapters.CalenderDetail;
import com.digicraft.wateradventures.Models.SaleModel;
import com.digicraft.wateradventures.Network.HttpManager;
import com.digicraft.wateradventures.Network.RequestPackage;
import com.digicraft.wateradventures.Network.Url;
import com.digicraft.wateradventures.Parser.SalesParser;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {


    public CalenderFragment() {
        // Required empty public constructor
    }

    ProgressBar progressBar;
    GridView gridView;
    ListView listView;
    String authToken;
    Date date;
    CalendarView calenderView;
    ArrayList<SaleModel> salesModels;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calender, container, false);
        progressBar = root.findViewById(R.id.saleProgressBar);
        listView = root.findViewById(R.id.calender_detail_lv);

        salesModels = new ArrayList<>();
        calenderView = root.findViewById(R.id.calenderView);

        calenderView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date = new Date(year, month, dayOfMonth);

                String monthStr = String.valueOf(month+1);
                String dayStr = String.valueOf(dayOfMonth);

                if (month+1 < 10)
                {
                    monthStr = "0"+String.valueOf(month+1);
                }

                if (dayOfMonth < 10)
                {
                    dayStr = "0"+String.valueOf(dayOfMonth);
                }

              String d = year+"-"+monthStr+"-"+dayStr;

                filterList(d);

            }
        });

        senddatatoserver();



        return root;
    }




    public void senddatatoserver() {
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

    public void getAllSales(String date) {
        JSONObject post_dict = new JSONObject();

        // {"date":"2018-08-14","calendarView":"month"}
        try {
            post_dict.put("date", date);
            post_dict.put("calenderView", "month");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (post_dict.length() > 0) {
            new getAllSalesTask().execute(String.valueOf(post_dict));
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
                //showToast(token);
                authToken = token;
                getAllSales("2018-08-14");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class getAllSalesTask extends  AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(Url.GET_ALL_SALES);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("WA-AUTH-TOKEN", authToken);
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
                salesModels = SalesParser.getAllSalesParse(s);
                updateList(salesModels);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public void showToast(String msg) {
       Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void updateList(ArrayList<SaleModel> models)
    {
        progressBar.setVisibility(View.GONE);
        CalenderDetail detail_adapter = new CalenderDetail(getActivity() , models);
        listView.setAdapter(detail_adapter);
    }

    public void filterList(String date)
    {
        ArrayList<SaleModel> filteredList = new ArrayList<>();

        for (SaleModel model : salesModels)
        {
            String activityDate = model.activityTimeStart.substring(0,10);
           // showToast(activityDate);

            SimpleDateFormat formatter;

            try{

                formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = formatter.parse(date);
                Date date2 = formatter.parse(activityDate);

                if (date1.compareTo(date2) == 0)
                {
                    filteredList.add(model);
                }

            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

        }

        updateList(filteredList);


    }


}
