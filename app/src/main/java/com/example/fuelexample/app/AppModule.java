package com.example.fuelexample.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ath.fuel.FuelModule;
import com.ath.fuel.Lazy;
import com.example.fuelexample.data.SomeDataPojo;
import com.example.fuelexample.ui.main.MainDataPojo;
import com.example.fuelexample.ui.main.MainFuelModule;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;

public class AppModule extends FuelModule {
    public AppModule(Application app) {
        super(app);
    }

    @Override protected void configure(@NonNull Application app) {
        super.configure(app);

        bind(AppScopedSingleton.class, new AppScopedSingleton()); // this ok because we're binding a app singleton

        bind(ActivityScopeSingleton.class, (lazy, parent) -> new ActivityScopeSingleton());

        bind(SomeDataPojo.class, ((lazy, parent) -> {
            SomeDataPojo pojo = new SomeDataPojo();
            pojo.setSomeValue("Hello World");
            return pojo;
        }));

        // A package wants to keep manage its own dependencies
        addModule(new MainFuelModule());

        // This is a test
        // This class is mapped in MainFuelModule as well
        // This demonstrates that a warning is logged when a type is mapped more than once.
        bind(MainDataPojo.class, (lazy, parent) -> new MainDataPojo("HI! HI! HI!"));

    }

    @Nullable @Override protected <T> T onInstanceUnattainable(@NonNull Lazy<T> lazy) {
        //noinspection unchecked
        return (T) REFLECTIVE_PROVIDER.provide(lazy, null);
    }

}
