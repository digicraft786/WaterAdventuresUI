package com.digicraft.wateradventures.Models;

/**
 * Created by mac on 19/08/2018.
 */

public class SaleModel {

    public String activityName;
    public String activityTimeStart;
    public String activityTimeEnd;
    public String activityParticipants;
    public String activityIcon;
    public String customerFirstName;
    public String customerLastName;
    public String customerTelephone;
    public String customerEmail;

    public String hexColor;

    public SaleModel()
    {}

    @Override
    public String toString() {
        return " "+activityName+" "+customerFirstName+" "+customerLastName;
    }
}
