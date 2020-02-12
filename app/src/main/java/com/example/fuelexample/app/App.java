package com.example.fuelexample.app;

import android.app.Application;

import com.ath.fuel.FuelInjector;

public class App extends Application {

    @Override public void onCreate() {
        FuelInjector.get().ignite(this, new AppModule(this));
        super.onCreate();
    }

}
