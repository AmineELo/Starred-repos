package com.example.amineelouattar.starredrepos.main_activity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.utils.VolleySingleton;

public class MainActivityModel implements MainModelInterface {

    @Override
    public void performRequest(String url, int pager, Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        //setup a string request to get reposDataSet list from the crafted url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + pager,
                responseListener,
                errorListener);

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
