package com.example.fuelexample.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ath.fuel.FuelModule;
import com.ath.fuel.Lazy;
import com.example.fuelexample.data.SomeDataPojo;
import com.example.fuelexample.ui.main.MainActivity.MainActFuelModule;
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

        //addModule(m -> { m.bind(MainActivity.class, MainActFuelModule.class); });

        addModule(new MainActFuelModule());

        /*

        In AppModule:

        addModule( MyXyzAppModule )


        In MyXyzAppModule:
        bind( appSingleton )
        bind( appSingleton )
        bind( activitySingleton )
        bind( activitySingleton )
        bind( viewRootSingleton )
        bind( viewRootSingleton )



         */


    }

    @Nullable @Override protected <T> T onInstanceUnattainable(@NonNull Lazy<T> lazy) {
        //noinspection unchecked
        return (T) REFLECTIVE_PROVIDER.provide(lazy, null);
    }
}
