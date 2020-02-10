package com.example.fuelexample.app;

import android.app.Application;

import com.ath.fuel.FuelInjector;

public class App extends Application {

    @Override public void onCreate() {
        FuelInjector.initializeModule(new AppModule(this));
        super.onCreate();
    }

}
