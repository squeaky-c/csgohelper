package com.spinfused.csgohelper;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

public class JsonStatisticsController {

    private final int TAG = 100;

    private OnResponseListener responseListener;

    public JsonStatisticsController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        String url = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=6B6CF7FCEE8B7BD77FCED63EAF2AFFA8&steamids="+Uri.encode(query);

        // Create new request using JsonRequest
        JsonStatisticsRequest request = new JsonStatisticsRequest(method, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String names) {
                        Log.d("JsonController.java","Success");
                        responseListener.onSuccess(names);
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
        void onSuccess(String names);
        void onFailure(String errorMessage);
    }

}