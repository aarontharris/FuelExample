package com.example.fuelexample.core.os;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ath.fuel.FuelInjector;

public class CoreFragment extends Fragment {

    @CallSuper
    @Override public void onAttach(@NonNull Context context) {
        FuelInjector.get().ignite(context, this);
        super.onAttach(context);
    }

}
