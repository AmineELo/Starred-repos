package com.example.amineelouattar.starredrepos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.amineelouattar.starredrepos.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.utils.GlobalVars;
import com.example.amineelouattar.starredrepos.utils.ReposAdapter;
import com.example.amineelouattar.starredrepos.utils.VolleySingleton;
import com.example.amineelouattar.starredrepos.models.Repos;
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

public class MainActivity extends AppCompatActivity implements MainViewInterface{

    private RecyclerView reposList;
    private List<Repos> reposDataSet;
    private int pager = 0;
    private ReposAdapter adapter;
    private boolean isLoading = false;
    private Paginate paginate;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        configureReposList();

        //create the Paginate Builder
        paginate = Paginate.with(reposList, presenter)
                .setLoadingTriggerThreshold(3)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .build();

    }

    private void initComponents(){
        reposDataSet = new ArrayList<>();
        reposList = findViewById(R.id.repos_list);
        adapter = new ReposAdapter(reposDataSet, this);
        MainModelInterface mainModel = new MainActivityModel();
        presenter = new MainActivityPresenter(mainModel, this);
    }

    private void configureReposList(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(reposList.getContext(), mLayoutManager.getOrientation());

        reposList.setLayoutManager(mLayoutManager);

        //Set the divider for the list
        reposList.addItemDecoration(dividerItemDecoration);
        //Set the Repos Adapter for the list
        reposList.setAdapter(adapter);
    }

    @Override
    public void updateRepos(List<Repos> reposList) {

    }
}
