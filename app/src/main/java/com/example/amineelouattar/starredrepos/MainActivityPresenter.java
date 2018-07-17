package com.example.amineelouattar.starredrepos;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.amineelouattar.starredrepos.interfaces.MainPresenterInterface;
import com.example.amineelouattar.starredrepos.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.models.Repos;
import com.example.amineelouattar.starredrepos.utils.GlobalVars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivityPresenter implements MainPresenterInterface{

    MainViewInterface view;
    private MainActivityModel mainModel;


    public MainActivityPresenter(MainActivityModel mainModel) {
        this.mainModel = mainModel;
    }

    public void bind(MainViewInterface view){
        this.view = view;
    }

    public void unbind(){
        this.view = null;
    }

    @Override
    public void getRepos(int pager) {

        mainModel.performRequest(setUpUrl(), pager, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(view != null){
                    view.updateRepos(extractReposList(response));
                }
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
}
