package com.example.amineelouattar.starredrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.amineelouattar.starredrepos.Utils.GlobalVars;
import com.example.amineelouattar.starredrepos.Utils.ReposAdapter;
import com.example.amineelouattar.starredrepos.Utils.Volleysingleton;
import com.example.amineelouattar.starredrepos.models.Repos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView repos_list;
    private List<Repos> repos;
    private int pager = 1;
    private ReposAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize components
        repos_list = (RecyclerView) findViewById(R.id.repos_list);
        repos = new ArrayList<>();
        adapter = new ReposAdapter(repos, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(repos_list.getContext(), mLayoutManager.getOrientation());

        repos_list.setLayoutManager(mLayoutManager);

        //Set the divider for the list
        repos_list.addItemDecoration(dividerItemDecoration);
        //Set the Repos Adapter for the list
        repos_list.setAdapter(adapter);
        //Get data from the Api and notify that data set changed
        apiRequest();


    }

    private void apiRequest(){

        String url = setUpUrl();

        //setup a string request to get repos list from the crafted url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + pager,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //print the response
                        Log.d("VOLLEY_NETWORKS", response);

                        try{
                            //Parse the String response to a JSON object
                            JSONObject data = (JSONObject) new JSONTokener(response).nextValue();
                            //Retrieve the items Array
                            JSONArray items = data.getJSONArray("items");

                            // iterate through each item and needed field the dataSet
                            for(int i = 0; i < items.length(); i++){
                                repos.add(new Repos(
                                        items.getJSONObject(i).getString("name"),
                                        items.getJSONObject(i).getJSONObject("owner").getString("avatar_url"),
                                        items.getJSONObject(i).getJSONObject("owner").getString("login"),
                                        items.getJSONObject(i).getString("description"),
                                        items.getJSONObject(i).getString("stargazers_count")

                                ));
                            }

                            adapter.notifyDataSetChanged();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //Add request to Volley queue;
        Volleysingleton.getInstance(this).addToRequestQueue(stringRequest);
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
