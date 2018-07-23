package com.example.amineelouattar.starredrepos.main_activity.module;

import android.content.Context;

import com.example.amineelouattar.starredrepos.utils.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @ActivityScope
    @Provides
    public Context provideContext(){
        return this.context;
    }
}
