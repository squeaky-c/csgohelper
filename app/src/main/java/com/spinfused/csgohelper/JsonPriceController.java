package com.spinfused.csgohelper;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

public class JsonPriceController {

    private final int TAG = 100;
    private OnResponseListener responseListener;

    public JsonPriceController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        String url = "http://steamcommunity.com/market/priceoverview/?country=US&currency=0&appid=730&market_hash_name="+Uri.encode(query);


        // Create new request using JsonRequest
        JsonInventoryPriceRequest request = new JsonInventoryPriceRequest(method, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String itemPrice) {
                        Log.d("JsonPriceController","Success");
                        responseListener.onSuccess(itemPrice);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JsonPriceController","Failure");
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
        void onSuccess(String itemPrice);
        void onFailure(String errorMessage);
    }

}