package com.example.fuelexample.ui.play;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreActivity;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.ui.play.fragment.play.PlayFragment;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;
import com.example.fuelexample.ui.singleton.SomeFancySingleton;

public class PlayActivity extends CoreActivity {

    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);
    private final @NonNull Lazy<SomeFancySingleton> lSomeFancySingleton = Lazy.attain(this, SomeFancySingleton.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.play_activity, PlayFragment.newInstance())
                    .commitNow();
        }

        Log.d("PlayActivity...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
        lSomeFancySingleton.get().doSomething();
    }
}
