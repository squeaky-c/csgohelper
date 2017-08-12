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

public class JsonStatisticsRequest extends Request<String> {

    // Success listener implemented in controller
    private Response.Listener<String> successListener;

    public JsonStatisticsRequest(int method, String url, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.successListener = successListener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String jsonString = new String(response.data);
        String names;
        JSONObject jsonObject;
        Log.i(this.getClass().getName(), jsonString);
        try {
            jsonObject = new JSONObject(jsonString);
            names = StatisticsFragment.parseJson(jsonObject);
        }
        catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new VolleyError("Failed to process the request"));
        }
        return Response.success(names, getCacheEntry());
    }

    @Override
    protected void deliverResponse(String names) {
        successListener.onResponse(names);
    }
}
