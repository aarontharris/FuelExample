package com.example.fuelexample.ui.singleton;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.example.fuelexample.core.di.ActivityScope;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.data.SomeDataPojo;

@ActivityScope
public class SomeFancySingleton {

    private final @NonNull Activity activity; // whatever
    private final @NonNull SomeDataPojo pojo;

    public SomeFancySingleton(@NonNull Activity activity, @NonNull SomeDataPojo pojo) {
        this.activity = activity;
        this.pojo = pojo;
    }

    public void doSomething() {
        Log.d("SomeFancySingleton.doSomething -- %s! %s", pojo.getSomeValue(), hashCode());
    }

    protected @NonNull SomeDataPojo getPojo() {
        return pojo;
    }
}
