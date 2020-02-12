package com.example.fuelexample.ui.common.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.core.util.Views;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;
import com.example.fuelexample.ui.singleton.MyViewRootSingleton;

public class SharedCustomView2 extends LinearLayoutCompat {
    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);
    private final @NonNull Lazy<MyViewRootSingleton> lMyViewRootSingleton = Lazy.attain(this, MyViewRootSingleton.class);
    private final @NonNull Lazy<MyViewRootSingleton> lMyViewRootSingleton2 = Lazy.attain(this, MyViewRootSingleton.class);

    public SharedCustomView2(Context context) {
        this(context, null);
    }

    public SharedCustomView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SharedCustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Views.merge(this, R.layout.shared_custom_view);
        setOrientation(VERTICAL);
    }

    @Override protected void onAttachedToWindow() {
        FuelInjector.get().igniteViewRoot(this);
        super.onAttachedToWindow();

        Log.d("SharedCustomView2...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
        lMyViewRootSingleton.get().doSomething();
        lMyViewRootSingleton2.get().doSomething();
    }
}
