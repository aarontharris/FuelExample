package com.example.fuelexample.core.os;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CoreActivity extends AppCompatActivity implements HasAndroidInjector {
    @Inject DispatchingAndroidInjector<Object> injector;

    @Override public AndroidInjector<Object> androidInjector() {
        return injector;
    }

    @CallSuper
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }
}
