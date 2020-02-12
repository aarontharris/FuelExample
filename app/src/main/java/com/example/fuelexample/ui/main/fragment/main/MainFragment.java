package com.example.fuelexample.ui.main.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.core.util.Pre;
import com.example.fuelexample.ui.common.fragment.exampleshared.SharedFragment;
import com.example.fuelexample.ui.main.MainViewModel;
import com.example.fuelexample.ui.play.PlayActivity;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;
import com.example.fuelexample.ui.singleton.MyViewRootSingleton;

import static com.example.fuelexample.core.util.Views.findView;


public class MainFragment extends CoreFragment {

    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);
    private final @NonNull Lazy<MyViewRootSingleton> lMyViewRootSingleton = Lazy.attain(this, MyViewRootSingleton.class);


    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        //FuelInjector.get().ignite(context, this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_bottom_container, SharedFragment.newInstance())
                .commitNow();
        //noinspection ConstantConditions
        return FuelInjector.get().igniteViewRootFragment(inflater.inflate(R.layout.main_fragment, container, false), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view, R.id.main_fragment_message).setOnClickListener(v -> {
            Pre.notNull(getActivity()).startActivity(new Intent(getActivity(), PlayActivity.class));
        });

        Log.d("MainFragment...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
        lMyViewRootSingleton.get().doSomething();
    }
}
