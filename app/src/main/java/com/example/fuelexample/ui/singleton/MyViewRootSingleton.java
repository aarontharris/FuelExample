package com.example.fuelexample.ui.singleton;

import com.ath.fuel.ViewRootSingleton;
import com.example.fuelexample.core.util.Log;

@ViewRootSingleton
public class MyViewRootSingleton {

    public void doSomething() {
        Log.d("MyViewRootSingleton.doSomething -- %s", hashCode());
    }

}
