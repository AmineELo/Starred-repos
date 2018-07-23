package com.example.amineelouattar.starredrepos;

import android.app.Application;

import com.example.amineelouattar.starredrepos.data.component.AppComponent;
import com.example.amineelouattar.starredrepos.data.component.DaggerAppComponent;
import com.example.amineelouattar.starredrepos.data.module.AppModule;

public class AppEntry extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
