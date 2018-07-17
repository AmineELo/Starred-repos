package com.example.amineelouattar.starredrepos;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.amineelouattar.starredrepos.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.utils.GlobalVars;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivityModel implements MainModelInterface {

    @Override
    public StringRequest performRequest(String url, int pager, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        //setup a string request to get reposDataSet list from the crafted url
        return new StringRequest(Request.Method.GET, url + pager,
                responseListener,
                errorListener);

    }
}
