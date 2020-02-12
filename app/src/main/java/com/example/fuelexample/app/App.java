package com.example.fuelexample.app;

import android.app.Application;

import com.ath.fuel.FuelInjector;

public class App extends Application {

    @Override public void onCreate() {
//        FuelInjector.get().initializeModule(this, new AppModule(this));
//        FuelInjector.get().ignite(this, this);
        FuelInjector.get().ignite(this, new AppModule(this));
        super.onCreate();
    }

}
