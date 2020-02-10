package com.example.fuelexample.ui.singleton;

import androidx.annotation.NonNull;

import com.ath.fuel.ActivitySingleton;
import com.ath.fuel.Lazy;
import com.example.fuelexample.core.util.Log;

@ActivitySingleton
public class ActivityScopeSingleton {

    public static final @NonNull Lazy<ActivityScopeSingleton> attain(@NonNull Object parent) {
        return Lazy.attain(parent, ActivityScopeSingleton.class);
    }

    public void doSomething() {
        Log.d("ActivityScopeSingleton.doSomething " + hashCode());
    }
   
}
