package com.example.amineelouattar.starredrepos.main_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.amineelouattar.starredrepos.AppEntry;
import com.example.amineelouattar.starredrepos.R;
import com.example.amineelouattar.starredrepos.main_activity.component.DaggerMainActivityComponent;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.main_activity.module.ContextModule;
import com.example.amineelouattar.starredrepos.main_activity.module.MainActivityModule;
import com.example.amineelouattar.starredrepos.utils.ReposAdapter;
import com.example.amineelouattar.starredrepos.main_activity.models.Repos;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewInterface{

    private RecyclerView reposList;
    private ReposAdapter adapter;
    @Inject MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDaggerComponent();
        initComponents();
        configureReposList();

        //create the Paginate Builder
        Paginate.with(reposList, presenter)
                .setLoadingTriggerThreshold(3)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .build();


    }

    private void initComponents(){
        List<Repos> reposDataSet = new ArrayList<>();
        reposList = findViewById(R.id.repos_list);
        adapter = new ReposAdapter(reposDataSet, this);
    }

    private void initDaggerComponent(){
        DaggerMainActivityComponent.builder()
                .appComponent(((AppEntry)getApplicationContext()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
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
        adapter.addRepos(reposList);
    }
}
