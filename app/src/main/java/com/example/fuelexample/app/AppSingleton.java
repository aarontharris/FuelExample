package com.example.fuelexample.app;

import com.example.fuelexample.core.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppSingleton {

    @Inject AppSingleton() {}

    public void doSomething() {
        Log.d("AppSingleton.doSomething " + hashCode());
    }

}
