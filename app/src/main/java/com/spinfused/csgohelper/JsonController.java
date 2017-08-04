package com.spinfused.csgohelper;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;


import java.util.List;

public class JsonController {

    private final int TAG = 100;

    private OnResponseListener responseListener;

    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest(String query){

        query = "76561197962695731";

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        String url = "http://steamcommunity.com/inventory/"+Uri.encode(query)+"/730/2?l=english&count=5000";
        //String url = "http://api.steampowered.com/ISteamUserStats/GetSchemaForGame/v2/?key=6B6CF7FCEE8B7BD77FCED63EAF2AFFA8&appid=" + Uri.encode(query);

        // Create new request using JsonRequest
        JsonRequest request = new JsonRequest(method, url, new Response.Listener<List<InventoryItem>>() {
                    @Override
                    public void onResponse(List<InventoryItem> inventory) {
                        Log.d("JsonController.java","Success");
                        responseListener.onSuccess(inventory);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JsonController.java","Failure");
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    public interface OnResponseListener {
        void onSuccess(List<InventoryItem> inventory);
        void onFailure(String errorMessage);
    }

}