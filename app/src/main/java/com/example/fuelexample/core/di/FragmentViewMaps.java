package com.example.fuelexample.core.di;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fuelexample.core.util.Doc.Managed;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import dagger.android.HasAndroidInjector;

public final class FragmentViewMaps { // TODO: move to DI ?

    private static @Managed WeakHashMap<Context, FragmentViewMaps> CONTEXT_SINGLETONS = new WeakHashMap<>();
    private static @NonNull final Object lock = new Object();

    public static synchronized @NonNull FragmentViewMaps get(@NonNull final Context context) {
        FragmentViewMaps maps = null;
        if ((maps = CONTEXT_SINGLETONS.get(context)) == null) {
            synchronized (lock) {
                if ((maps = CONTEXT_SINGLETONS.get(context)) == null) {
                    maps = new FragmentViewMaps();
                    CONTEXT_SINGLETONS.put(context, maps);
                }
            }
        }
        return maps;
    }

    private WeakHashMap<View, WeakReference<HasAndroidInjector>> map = new WeakHashMap<>();

    private FragmentViewMaps() { }

    public void associate(View view, HasAndroidInjector hasAndroidInjector) {
        map.put(view, new WeakReference<>(hasAndroidInjector));
    }

    public @Nullable HasAndroidInjector lookup(View view) {
        WeakReference<HasAndroidInjector> wInjector = map.get(view);
        return wInjector == null ? null : wInjector.get();
    }

}
