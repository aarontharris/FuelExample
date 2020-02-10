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

import com.ath.fuel.Lazy;
import com.example.fuelexample.R;
import com.example.fuelexample.app.AppScopedSingleton;
import com.example.fuelexample.core.os.CoreFragment;
import com.example.fuelexample.core.util.Log;
import com.example.fuelexample.ui.singleton.ActivityScopeSingleton;

import static com.example.fuelexample.core.util.Views.findView;


// Add my SharedFragmentModule to your ActivityComponent or FragmentComponent
public class SharedFragment extends CoreFragment {

    private final @NonNull Lazy<AppScopedSingleton> lAppScopedSingleton = AppScopedSingleton.attain(this);
    private final @NonNull Lazy<ActivityScopeSingleton> lActivityScopedSingleton = ActivityScopeSingleton.attain(this);

    private SharedViewModel mViewModel;

    public static SharedFragment newInstance() {
        return new SharedFragment();
    }

    @Override public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d("SharedFragment...");
        lAppScopedSingleton.get().doSomething();
        lActivityScopedSingleton.get().doSomething();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shared_fragment, container, false);
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
