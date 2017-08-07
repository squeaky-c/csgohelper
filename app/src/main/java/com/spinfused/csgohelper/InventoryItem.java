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

    public String itemName; //Item name - Basic, ie "M4A1-S | Flashback"



    public String itemMarketHashName; //Item hash name - Advanced, for price lookup
    public String itemDescription; //Item description, ie "Exterior: Field-Tested"
    public String itemPrice; //Median sale price
    public String itemIcon; //Item icon URL

    public static List<InventoryItem> parseJson(JSONObject jsonObject) throws JSONException {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        Log.d("JSON: Inventory","Made ArrayList");
        if(jsonObject.has("success")){
            Log.d("JSON: Inventory","Success on finding Inventory JSON");
            JSONArray jsonArray = jsonObject.getJSONArray("descriptions");
            for(int i = 0; i < jsonArray.length(); i++){
                inventoryItems.add(new InventoryItem(jsonArray.getJSONObject(i)));
            }

        }

        return inventoryItems;
    }

    public static String parseJsonPrice(JSONObject jsonObject) throws JSONException {
        JSONObject test;
        String itemPrice = "";
        Log.d("JSON: Inventory","Made item price string");
        if(jsonObject.has("success")){
            Log.d("JSON: Inventory","Found price");
            itemPrice = jsonObject.getString("median_price");
        }
        return itemPrice;
    }

    private InventoryItem(JSONObject jsonObject) throws JSONException {
        JsonController controller = new JsonController(new JsonController.OnResponseListener() {
            @Override
            public void onSuccess(List<InventoryItem> inventory) {
                Log.d("JSON: Inventory","Made controller for price");
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("JSON: Inventory","Failed to fetch price from Steam JSON.");
            }
        });

        if(jsonObject.has("icon_url")) this.setItemIcon(jsonObject.getString("icon_url"));
        if(jsonObject.has("descriptions")) this.setItemDescription(jsonObject.getJSONArray("descriptions").getJSONObject(0).getString("value"));
        if(jsonObject.getString("type").equals("Base Grade Container")) this.setItemDescription("Base Grade Container");
        if(jsonObject.has("name")) this.setItemName(jsonObject.getString("name"));




    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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
    }

    public String getItemMarketHashName() {
        return itemMarketHashName;
    }

    public void setItemMarketHashName(String itemMarketHashName) {
        this.itemMarketHashName = itemMarketHashName;
    }

}
