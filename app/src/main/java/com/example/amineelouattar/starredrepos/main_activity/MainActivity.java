package com.example.amineelouattar.starredrepos.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.amineelouattar.starredrepos.R;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.utils.ReposAdapter;
import com.example.amineelouattar.starredrepos.main_activity.models.Repos;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewInterface{

    private RecyclerView reposList;
    private int pager = 0;
    private ReposAdapter adapter;
    private boolean isLoading = false;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        configureReposList();

        //create the Paginate Builder
        Paginate paginate = Paginate.with(reposList, presenter)
                .setLoadingTriggerThreshold(3)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .build();

        presenter.bind(this);

    }

    private void initComponents(){
        List<Repos> reposDataSet = new ArrayList<>();
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
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void updateRepos(List<Repos> reposList) {
        adapter.addRepos(reposList);
    }
}
