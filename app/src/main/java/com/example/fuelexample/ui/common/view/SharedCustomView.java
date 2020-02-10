package com.example.fuelexample.ui.common.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.di.Di;
import com.example.fuelexample.core.util.Views;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

public class SharedCustomView extends LinearLayoutCompat {

    @Module(subcomponents = SharedCustomViewComponent.class)
    public abstract class SharedCustomViewModule {
        @Binds @IntoMap @ClassKey(SharedCustomView.class) abstract AndroidInjector.Factory<?> bindInjectorFactory(SharedCustomViewComponent.Factory factory);
    }

    @Subcomponent
    public interface SharedCustomViewComponent extends AndroidInjector<SharedCustomView> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<SharedCustomView> {
        }
    }

    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    public SharedCustomView(Context context) {
        this(context, null);
    }

    public SharedCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SharedCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        Views.merge(this, R.layout.shared_custom_view);
        setOrientation(VERTICAL);
    }

    @Override protected void onAttachedToWindow() {
        Di.inject(this);
        super.onAttachedToWindow();

        appSingleton.doSomething();
        activitySingleton.doSomething();
    }
}
