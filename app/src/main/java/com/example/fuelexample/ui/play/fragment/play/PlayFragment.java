package com.example.fuelexample.ui.play.fragment.play;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.ui.play.PlayViewModel;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


public class PlayFragment extends CoreFragment {

    @Module
    public abstract class PlayFragmentModule {
        @ContributesAndroidInjector
        abstract PlayFragment injector();
    }

    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    private PlayViewModel mViewModel;

    public static PlayFragment newInstance() {
        return new PlayFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        appSingleton.doSomething();
        activitySingleton.doSomething();
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
