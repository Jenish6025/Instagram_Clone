package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends  Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("UCGsUzLZIcJUMIlXs1lK5PFmaaZkSedpJPZPnPXZ")
                // if defined
                .clientKey("G7E91sLrmNAsSAAqdK6NRVYe6DkzepX7IyrDyj1H")
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
