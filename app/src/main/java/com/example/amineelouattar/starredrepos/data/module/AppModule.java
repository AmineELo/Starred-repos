package com.example.amineelouattar.starredrepos.data.module;

import android.app.Application;

import com.example.amineelouattar.starredrepos.main_activity.MainActivityModel;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainModelInterface;
import com.example.amineelouattar.starredrepos.main_activity.interfaces.MainViewInterface;
import com.example.amineelouattar.starredrepos.utils.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public Application provideApplication(){
        return application;
    }
}
