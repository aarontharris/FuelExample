package com.example.fuelexample.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreActivity;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.ui.main.fragment.main.MainFragment;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;
import com.example.fuelexample.ui.singleton.SomeFancySingleton;

public class MainActivity extends CoreActivity {

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
