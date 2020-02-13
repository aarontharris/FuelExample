package com.example.fuelexample.ui.main;

import androidx.annotation.NonNull;

import com.example.fuelexample.core.util.Log;

public class MainFancySingletonImpl implements MainFancySingleton {
    private final @NonNull MainDataPojo pojo;

    public MainFancySingletonImpl(@NonNull MainDataPojo pojo) {
        this.pojo = pojo;
    }

    @Override public void doSomething() {
        Log.d("MainFancySingleton.doSomething -- %s! %s", pojo.getSomeValue(), hashCode());
    }
}
