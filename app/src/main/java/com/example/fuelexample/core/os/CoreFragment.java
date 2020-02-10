package com.example.fuelexample.core.os;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fuelexample.core.di.Di;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class CoreFragment extends Fragment implements HasAndroidInjector {
    @Inject DispatchingAndroidInjector<Object> androidInjector;

    @CallSuper
    @Override public void onAttach(@NonNull Context context) {
        Di.inject(this);
        super.onAttach(context);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
