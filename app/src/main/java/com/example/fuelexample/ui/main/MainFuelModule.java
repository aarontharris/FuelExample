package com.example.fuelexample.ui.main;

import androidx.annotation.NonNull;

import com.ath.fuel.FuelConfigurator;
import com.ath.fuel.FuelModule.FuelSubmodule;
import com.ath.fuel.Lazy;

public class MainFuelModule implements FuelSubmodule {
    @Override public void configure(@NonNull FuelConfigurator m) {
        m.bind(MainDataPojo.class, (lazy, parent) -> new MainDataPojo("HI! HI! HI!"));

        m.bind(MainFancySingleton.class, MainFancySingletonImpl.class);
        m.bind(MainFancySingletonImpl.class, (l, p) -> {
            MainDataPojo pojo = Lazy.attain(p, MainDataPojo.class).get();
            return new MainFancySingletonImpl(pojo);
        });
    }
}
