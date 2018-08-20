package com.digicraft.wateradventures.Parser;

import com.digicraft.wateradventures.Models.SaleModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by mac on 19/08/2018.
 */

public class SalesParser {

    public static ArrayList<SaleModel> getAllSalesParse(String response) throws JSONException {
        ArrayList<SaleModel> models = new ArrayList<>();
        JSONArray arr = new JSONArray(response);
        for (int i = 0; i < arr.length(); i++) {
            SaleModel model = new SaleModel();
            model.activityName = arr.getJSONObject(i).getString("activityName");
            model.activityTimeStart = arr.getJSONObject(i).getString("activityTimeStart");
            model.activityTimeEnd = arr.getJSONObject(i).getString("activityTimeEnd");
            model.customerFirstName = arr.getJSONObject(i).getString("customerFirstName");
            model.customerLastName = arr.getJSONObject(i).getString("customerLastName");
            model.customerEmail = arr.getJSONObject(i).getString("customerEmail");
            model.hexColor = arr.getJSONObject(i).getString("hexColor");

            models.add(model);
        }
        return models;
    }

}
