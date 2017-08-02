package com.spinfused.csgohelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 7/27/2017.
 */

public class InventoryItem {

    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private String itemIcon;

    public static List<InventoryItem> parseJson(JSONObject jsonObject) throws JSONException {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        if(jsonObject.has("success")){
            JSONArray jsonArray = jsonObject.getJSONArray("rgDescriptions");
            for(int i = 0; i < jsonArray.length(); i++){
                inventoryItems.add(new InventoryItem(jsonArray.getJSONObject(i)));
            }
        }

        return inventoryItems;
    }

    private InventoryItem(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("market_name")) this.setItemName(jsonObject.getString("market_name"));
        if(jsonObject.has("icon_url_large")) this.setItemIcon(jsonObject.getString("icon_url_large"));

        JSONArray descArray = jsonObject.getJSONArray("descriptions");
        if(jsonObject.has("descriptions")) this.setItemDescription(descArray.getJSONObject(2).getString("value"));

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
        this.itemIcon = itemIcon;
    }



}
