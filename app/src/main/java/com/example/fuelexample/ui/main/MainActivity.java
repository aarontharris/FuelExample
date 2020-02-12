package com.example.fuelexample.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ath.fuel.FuelConfigurator;
import com.ath.fuel.FuelModule.FuelSubmodule;
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

    public static class MainDataPojo extends SomeDataPojo {
        public MainDataPojo(String data) {
            setSomeValue(data);
        }
    }

    public interface MainFancySingleton {
        void doSomething();
    }

    public static class MainFancySingletonImpl implements MainFancySingleton {
        private final @NonNull MainDataPojo pojo;

        public MainFancySingletonImpl(@NonNull MainDataPojo pojo) {
            this.pojo = pojo;
        }

        @Override public void doSomething() {
            Log.d("MainFancySingleton.doSomething -- %s! %s", pojo.getSomeValue(), hashCode());
        }
    }

    public static class MainActFuelModule implements FuelSubmodule {
        @Override public void configure(@NonNull FuelConfigurator m) {
            m.bind(MainDataPojo.class, (lazy, parent) -> new MainDataPojo("HI! HI! HI!"));
            //noinspection unchecked
            //m.bind(MainFancySingleton.class, m.REFLECTIVE_PROVIDER);

            m.bind(MainFancySingleton.class, MainFancySingletonImpl.class);
            m.bind(MainFancySingletonImpl.class, (l, p) -> {
                MainDataPojo pojo = Lazy.attain(p, MainDataPojo.class).get();
                return new MainFancySingletonImpl(pojo);
            });
        }
    }


    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);
    private final @NonNull Lazy<SomeFancySingleton> lSomeFancySingleton = Lazy.attain(this, SomeFancySingleton.class);
    private final @NonNull Lazy<MainFancySingleton> lMainFancySingleton = Lazy.attain(this, MainFancySingleton.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        lMainFancySingleton.get().doSomething();
    }
}
