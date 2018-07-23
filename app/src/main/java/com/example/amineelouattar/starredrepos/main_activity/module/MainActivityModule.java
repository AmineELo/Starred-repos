package com.example.amineelouattar.starredrepos.main_activity.module;

import com.example.amineelouattar.starredrepos.main_activity.MainActivityModel;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.utils.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainViewInterface viewInterface;

    public MainActivityModule(MainViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    @ActivityScope
    @Provides
    public MainViewInterface provideMainViewInterface(){
        return viewInterface;
    }

    @ActivityScope
    @Provides
    public MainModelInterface provideMainModelInterface(){
        return new MainActivityModel();
    }
}
