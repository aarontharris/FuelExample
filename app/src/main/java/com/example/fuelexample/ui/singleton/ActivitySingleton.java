package com.example.fuelexample.ui.singleton;

import com.example.fuelexample.core.di.ActivityScope;
import com.example.fuelexample.core.util.Log;

import javax.inject.Inject;

@ActivityScope
public class ActivitySingleton {

    @Inject ActivitySingleton() {}

    public void doSomething() {
        Log.d("ActivitySingleton.doSomething " + hashCode());
    }

}
