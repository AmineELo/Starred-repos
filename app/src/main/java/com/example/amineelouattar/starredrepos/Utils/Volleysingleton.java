package com.example.amineelouattar.starredrepos.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by amineelouattar on 12/30/17.
 */

public class Volleysingleton {

    private static Volleysingleton volleyInstance;
    private RequestQueue queue;
    private Context context;

    private Volleysingleton(Context context){
        this.context = context;
        queue = getQueue();
    }

    private RequestQueue getQueue(){
        //RequestQue will be instantiated with the application context, to last for the entire lifetime of the app
        if (queue == null) queue = Volley.newRequestQueue(context.getApplicationContext());
        return queue;
    }

    public static synchronized Volleysingleton getInstance(Context context){
        if (volleyInstance == null ) volleyInstance = new Volleysingleton(context);
        return volleyInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getQueue().add(req);
    }
}
