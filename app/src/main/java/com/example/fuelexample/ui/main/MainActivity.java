package com.example.fuelexample.ui.main;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.FuelSubmodule;
import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreActivity;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.data.SomeDataPojo;
import com.example.fuelexample.ui.main.fragment.main.MainFragment;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;
import com.example.fuelexample.ui.singleton.SomeFancySingleton;

public class MainActivity extends CoreActivity {

    public static class MainActFuelModule extends FuelSubmodule {
        public MainActFuelModule(Activity activity) {
            super(activity);
        }

        @Override protected void configure() {
            super.configure();

            bind(SomeFancySingleton.class, new FuelProvider<SomeFancySingleton>() {
                @Override public SomeFancySingleton provide(Lazy lazy, Object parent) {
                    SomeDataPojo pojo = new SomeDataPojo();
                    pojo.setSomeValue("Hello Main");
                    return new SomeFancySingleton((Activity) lazy.getContext(), pojo) {
                        @Override public void doSomething() {
                            //super.doSomething();
                            Log.d("MainFancySingleton.doSomething -- %s! %s", getPojo().getSomeValue(), hashCode());
                        }
                    };
                }
            });
        }
    }


    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);
    private final @NonNull Lazy<SomeFancySingleton> lSomeFancySingleton = Lazy.attain(this, SomeFancySingleton.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FuelInjector.get().initializeModule(new MainActFuelModule(this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity, MainFragment.newInstance())
                    .commitNow();
        }

        Log.d("MainActivity...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
        lSomeFancySingleton.get().doSomething();
    }
}
