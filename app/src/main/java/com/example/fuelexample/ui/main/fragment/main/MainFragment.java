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

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.core.util.Pre;
import com.example.fuelexample.ui.common.fragment.exampleshared.SharedFragment;
import com.example.fuelexample.ui.common.fragment.exampleshared.SharedFragment.SharedFragmentModule;
import com.example.fuelexample.ui.main.MainViewModel;
import com.example.fuelexample.ui.play.PlayActivity;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

import static com.example.fuelexample.core.util.Views.findView;


public class MainFragment extends CoreFragment {

    @Module(subcomponents = MainFragmentComponent.class)
    public abstract class MainFragmentModule {

        @Binds
        @IntoMap
        @ClassKey(MainFragment.class)
        abstract AndroidInjector.Factory<?> bindMainFragmentInjectorFactory(MainFragmentComponent.Factory factory);

    }

    @Subcomponent(modules = SharedFragmentModule.class)
    public interface MainFragmentComponent extends AndroidInjector<MainFragment> {

        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<MainFragment> {
        }

    }

    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        appSingleton.doSomething();
        activitySingleton.doSomething();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_bottom_container, SharedFragment.newInstance())
                .commitNow();

        return inflater.inflate(R.layout.main_fragment, container, false);
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
    }
}
