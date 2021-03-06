package com.example.amineelouattar.starredrepos.data.component;

import android.app.Application;

import com.example.amineelouattar.starredrepos.data.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application application();
}
