package com.example.fuelexample.ui.common.fragment.exampleshared;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.di.FragmentViewMaps;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.ui.common.view.SharedCustomView.SharedCustomViewModule;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

import static com.example.fuelexample.core.util.Views.findView;


// Add my SharedFragmentModule to your ActivityComponent or FragmentComponent
public class SharedFragment extends CoreFragment {

    @Module(subcomponents = SharedFragmentComponent.class)
    public abstract class SharedFragmentModule {

        @Binds
        @IntoMap
        @ClassKey(SharedFragment.class)
        abstract AndroidInjector.Factory<?> bindMainFragmentInjectorFactory(SharedFragmentComponent.Factory factory);

    }

    @Subcomponent(modules = SharedCustomViewModule.class)
    public interface SharedFragmentComponent extends AndroidInjector<SharedFragment> {

        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<SharedFragment> {
        }

    }

    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    private SharedViewModel mViewModel;

    public static SharedFragment newInstance() {
        return new SharedFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        appSingleton.doSomething();
        activitySingleton.doSomething();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shared_fragment, container, false);
        FragmentViewMaps.get(view.getContext()).associate(view, this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view, R.id.shared_fragment_message).setOnClickListener(v -> {
            Toast.makeText(getContext(), "Clicky Clicky!", Toast.LENGTH_LONG).show();
        });
    }
}
