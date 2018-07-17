package com.example.amineelouattar.starredrepos;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.amineelouattar.starredrepos.interfaces.MainPresenterInterface;
import com.example.amineelouattar.starredrepos.utils.GlobalVars;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivityPresenter implements MainPresenterInterface{

    private MainActivityModel mainModel;

    public MainActivityPresenter(MainActivityModel mainModel) {
        this.mainModel = mainModel;
    }

    @Override
    public void getRepos(int pager) {

        mainModel.performRequest(setUpUrl(), pager, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private String setUpUrl(){
        // Get 1 month ago from current datetime to setUp the api query
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date currentDate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String url = GlobalVars.API_URL + GlobalVars.API_QUERY + format.format(currentDate) + GlobalVars.API_PARAMS;
        Log.d("CURRENT_DATE", url);
        return url;

    }
}
