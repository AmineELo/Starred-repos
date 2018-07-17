package com.example.amineelouattar.starredrepos.interfaces;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public interface MainModelInterface {
    StringRequest performRequest(String url, int pager, Response.Listener<String> responseListener, Response.ErrorListener errorListener);
}
