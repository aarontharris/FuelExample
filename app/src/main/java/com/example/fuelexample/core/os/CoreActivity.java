package com.example.fuelexample.core.os;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ath.fuel.FuelInjector;

public class CoreActivity extends AppCompatActivity {
    @CallSuper
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        FuelInjector.get().ignite(this, this);
        super.onCreate(savedInstanceState);
    }
}
