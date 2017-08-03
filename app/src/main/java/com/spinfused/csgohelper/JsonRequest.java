package com.spinfused.csgohelper;

/*
 * Created by abhijit on 12/2/16.
 * Edited by Sam on 07/23/16.
 */

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JsonRequest extends Request<List<InventoryItem>> {

    // Success listener implemented in controller
    private Response.Listener<List<InventoryItem>> successListener;

    public JsonRequest(int method, String url, Response.Listener<List<InventoryItem>> successListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<List<InventoryItem>> parseNetworkResponse(NetworkResponse response) {
        String jsonString = new String(response.data);
        List<InventoryItem> inventory;
        JSONObject jsonObject;
        Log.i(this.getClass().getName(), jsonString);
        try {
            jsonObject = new JSONObject(jsonString);
            inventory = InventoryItem.parseJson(jsonObject);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new VolleyError("Failed to process the request"));
        }
        return Response.success(inventory, getCacheEntry());
    }

    @Override
    protected void deliverResponse(List<InventoryItem> inventory) {
        successListener.onResponse(inventory);
    }
}
