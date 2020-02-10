package com.example.fuelexample.core.di;

import android.app.Activity;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.core.util.Pre;

import dagger.Lazy;
import dagger.android.HasAndroidInjector;

public final class Di {

    public static <T> T get(@NonNull Lazy<T> lazy) {
        return Pre.notNull(lazy.get());
    }

    private Di() { }

    public static void inject(@NonNull Fragment fragment) {
        Pre.notNull(Pre.notNull(Di.findHasAndroidInjectorForFragment(fragment)).androidInjector()).inject(fragment);
    }

    public static void inject(@NonNull View view) {
        Pre.notNull(Pre.notNull(Di.findHasAndroidInjectorForView(view)).androidInjector()).inject(view);
    }

    private static HasAndroidInjector findHasAndroidInjectorForFragment(@NonNull Fragment fragment) {
        Fragment parentFragment = fragment;
        while ((parentFragment = parentFragment.getParentFragment()) != null) {
            if (parentFragment instanceof HasAndroidInjector) {
                return (HasAndroidInjector) parentFragment;
            }
        }
        Activity activity = fragment.getActivity();
        if (activity instanceof HasAndroidInjector) {
            return (HasAndroidInjector) activity;
        }
        if (activity.getApplication() instanceof HasAndroidInjector) {
            return (HasAndroidInjector) activity.getApplication();
        }
        throw new IllegalArgumentException(
                String.format("No injector was found for %s", fragment.getClass().getCanonicalName()));
    }

    private static @NonNull HasAndroidInjector findHasAndroidInjectorForView(@NonNull View view) {
        ViewParent viewParent = view.getParent();
        while (viewParent != null) {
            Log.d("ViewParent: " + viewParent);
            if (viewParent instanceof HasAndroidInjector) {
                Log.d("Got Injector 1 @ " + viewParent);
                return (HasAndroidInjector) viewParent;
            }

            HasAndroidInjector injector = FragmentViewMaps.get(view.getContext()).lookup((View) viewParent);
            if (injector != null) {
                Log.d("Got Injector 2 @ " + viewParent);
                viewParent = viewParent.getParent();
                return injector;
            }
        }
        throw new IllegalArgumentException(
                String.format("No injector was found for %s", view.getClass().getCanonicalName()));
    }
}
