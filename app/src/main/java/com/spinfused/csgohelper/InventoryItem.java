package com.spinfused.csgohelper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 7/27/2017.
 */

public class InventoryItem {

    public String itemName; //Market hash name
    public String itemDescription; //Market description
    public String itemPrice; //Median sale price
    public String itemIcon; //Item icon URL

    public static List<InventoryItem> parseJson(JSONObject jsonObject) throws JSONException {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        Log.d("InventoryItem.java","Made ArrayList");
        //if(jsonObject.has("success")){
        if(jsonObject.has("success")){
            Log.d("InventoryItem.java","Success on finding JSON");


            /*JSONObject rgDescriptionsObject = jsonObject.getJSONObject("rgDescriptions");

            for (var invItem : rgDescriptionsObject.keys()) {
                inventoryItems.add(new InventoryItem(invItem.getJSONObject(invItem)));
            }

            for(int i = 0; i < jsonArray.length(); i++){
                //inventoryItems.add(new InventoryItem(jsonArray.getJSONObject(i)));
            }
            */

            //JSONArray jsonArray = jsonObject.getJSONObject("game").getJSONObject("availableGameStats").getJSONArray("achievements");
            JSONArray jsonArray = jsonObject.getJSONArray("descriptions");
            for(int i = 0; i < jsonArray.length(); i++){
                inventoryItems.add(new InventoryItem(jsonArray.getJSONObject(i)));
            }

        }

        return inventoryItems;
    }

    private InventoryItem(JSONObject jsonObject) throws JSONException {
        /*
        if(jsonObject.has("market_name")) this.setItemName(jsonObject.getString("market_name"));
        if(jsonObject.has("icon_url_large")) this.setItemIcon(jsonObject.getString("icon_url_large"));

        JSONArray descArray = jsonObject.getJSONArray("descriptions");
        if(jsonObject.has("descriptions")) this.setItemDescription(descArray.getJSONObject(2).getString("value"));
        */



        if(jsonObject.has("icon_url")) this.setItemIcon(jsonObject.getString("icon_url"));
        if(jsonObject.has("type")) this.setItemDescription(jsonObject.getString("type"));
        if(jsonObject.has("market_hash_name")) this.setItemName(jsonObject.getString("market_hash_name"));

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        Log.d("Steam name",this.itemName);
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        Log.d("Steam desc",this.itemDescription);
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String itemIcon) {
        this.itemIcon = "http://cdn.steamcommunity.com/economy/image/"+itemIcon;
        Log.d("Steam icon",this.itemIcon);


    }



}
