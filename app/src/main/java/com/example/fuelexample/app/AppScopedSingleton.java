package com.example.fuelexample.app;

import androidx.annotation.NonNull;

import com.ath.fuel.AppSingleton;
import com.ath.fuel.Lazy;
import com.example.fuelexample.core.util.Log;

@AppSingleton
public final class AppScopedSingleton {

    public static final @NonNull Lazy<AppScopedSingleton> attain(@NonNull Object parent) {
        return Lazy.attain(parent, AppScopedSingleton.class);
    }

    public void doSomething() {
        Log.d("AppScopedSingleton.doSomething " + hashCode());
    }

}
