package com.example.amineelouattar.starredrepos.main_activity.interfaces;

import android.content.Context;

import com.android.volley.Response;

public interface MainModelInterface {
    void performRequest(String url, int pager, Context context, Response.Listener<String> responseListener, Response.ErrorListener errorListener);
}
