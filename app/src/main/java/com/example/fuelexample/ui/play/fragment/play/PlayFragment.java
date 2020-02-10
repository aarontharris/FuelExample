package com.example.fuelexample.ui.play.fragment.play;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.ui.play.PlayViewModel;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;


public class PlayFragment extends CoreFragment {

    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);


    private PlayViewModel mViewModel;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d("PlayFragment...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.play_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
        // TODO: Use the ViewModel
    }
}
