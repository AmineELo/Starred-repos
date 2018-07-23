package com.example.amineelouattar.starredrepos.main_activity.component;

import com.example.amineelouattar.starredrepos.data.component.AppComponent;
import com.example.amineelouattar.starredrepos.main_activity.MainActivity;
import com.example.amineelouattar.starredrepos.main_activity.module.MainActivityModule;
import com.example.amineelouattar.starredrepos.utils.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MainActivityModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
