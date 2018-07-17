package com.example.amineelouattar.starredrepos.interfaces;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public interface MainModelInterface {
    void performRequest(String url, int pager, Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener);
}
