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

    //TODO: Implement item prices
    private String mName; //Item name - Basic, ie "M4A1-S | Flashback"
    private String mMarketHashName; //Item hash name - Advanced, for price lookup
    private String mDescription; //Item description, ie "Exterior: Field-Tested"
    private String mPrice; //Median sale price
    private String mIcon; //Item icon URL

    public static List<InventoryItem> parseJson(JSONObject jsonObject) throws JSONException {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        if(jsonObject.has("success")) {
            Log.d("JSON: Inventory","Success on finding Inventory JSON");
            JSONArray jsonArray = jsonObject.getJSONArray("descriptions");
            for(int i = 0; i < jsonArray.length(); i++){
                inventoryItems.add(new InventoryItem(jsonArray.getJSONObject(i)));
            }
        }
        return inventoryItems;
    }

    public static String parseJsonPrice(JSONObject jsonObject) throws JSONException {
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

        if(jsonObject.has("name")) this.setName(jsonObject.getString("name"));
        if(jsonObject.has("market_hash_name")) this.setMarketHashName(jsonObject.getString("market_hash_name"));
        if(jsonObject.has("icon_url")) this.setIcon(jsonObject.getString("icon_url"));
        if( jsonObject.getString("type").endsWith("Tag") ||
            jsonObject.getString("type").endsWith("Collectible") ||
            jsonObject.getString("type").endsWith("Graffiti") ||
            jsonObject.getString("type").endsWith("Sticker") ||
            jsonObject.getString("type").endsWith("Container")) {
            this.setDescription("Commodity");
        } else {
            this.setDescription(jsonObject.getJSONArray("descriptions").getJSONObject(0).getString("value"));
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getMarketHashName() {
        return mMarketHashName;
    }

    public void setMarketHashName(String mMarketHashName) {
        this.mMarketHashName = mMarketHashName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = "http://cdn.steamcommunity.com/economy/image/"+mIcon;
    }

}
