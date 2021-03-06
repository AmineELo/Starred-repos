package com.example.amineelouattar.starredrepos.main_activity;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainPresenterInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.main_activity.models.Repos;
import com.example.amineelouattar.starredrepos.utils.GlobalVars;
import com.paginate.Paginate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainActivityPresenter implements MainPresenterInterface, Paginate.Callbacks{

    private MainViewInterface view;
    private MainModelInterface mainModel;
    private int pager;
    private boolean isLoading;
    @Inject Context context;


    @Inject
    MainActivityPresenter(MainModelInterface mainModel, MainViewInterface view) {
        this.mainModel = mainModel;
        this.pager = 0;
        this.isLoading = false;
        this.view = view;
    }

    @Override
    public void getRepos() {

        mainModel.performRequest(setUpUrl(), pager, context, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                isLoading = false;
                if(view != null){
                    view.updateRepos(extractReposList(response));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                isLoading = false;
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

    private List<Repos> extractReposList(String response){

        List<Repos> reposList = new ArrayList<>();
        try {
            //Parse the String response to a JSON object
            JSONObject data = (JSONObject) new JSONTokener(response).nextValue();
            //Retrieve the items Array
            JSONArray items = data.getJSONArray("items");
            // iterate through each item and needed field the dataSet
            for(int i = 0; i < items.length(); i++){
                reposList.add(new Repos(
                        items.getJSONObject(i).getString("name"),
                        items.getJSONObject(i).getJSONObject("owner").getString("avatar_url"),
                        items.getJSONObject(i).getJSONObject("owner").getString("login"),
                        items.getJSONObject(i).getString("description"),
                        items.getJSONObject(i).getString("stargazers_count")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return reposList;
        }

        return reposList;
    }

    @Override
    public void onLoadMore() {
        isLoading = true;
        if(pager < GlobalVars.PAGE_LIMIT){
            //if the user didn't yet arrived to the bottom, increment the pager to get the next dataSet
            pager++;
            getRepos();
        }
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return pager >= GlobalVars.PAGE_LIMIT;
    }
}
