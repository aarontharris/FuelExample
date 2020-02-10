package com.example.fuelexample.ui.main;

import android.os.Bundle;

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.di.ActivityScope;
import com.example.fuelexample.core.os.CoreActivity;
import com.example.fuelexample.ui.main.fragment.main.MainFragment;
import com.example.fuelexample.ui.main.fragment.main.MainFragment.MainFragmentModule;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

public class MainActivity extends CoreActivity {

    @Module(subcomponents = MainComponent.class)
    public abstract class MainModule {

        @Binds
        @IntoMap
        @ClassKey(MainActivity.class)
        abstract AndroidInjector.Factory<?> bindMainAndroidInjectorFactory(MainComponent.Factory factory);

    /*

    // TODO: I did this, it worked see PlayFragment
    // NOTE:
    //  - For anything under an activity scope you wont need @ActivityScope
    //  - Instead of YourActivity, its YourFragment or YourView or YourWhatever


    Pro-tip: If your subcomponent and its factory have no other methods or supertypes other than the ones mentioned in step #2,
    you can use @ContributesAndroidInjector to generate them for you. Instead of steps 2 and 3, add an abstract module method
    that returns your activity, annotate it with @ContributesAndroidInjector, and specify the modules you want to install into
    the subcomponent. If the subcomponent needs scopes, apply the scope annotations to the method as well.

    @ActivityScope
    @ContributesAndroidInjector(modules = {  })
    abstract YourActivity contributeYourAndroidInjector();


     */
    }

    @ActivityScope
    @Subcomponent(modules = MainFragmentModule.class)
    public interface MainComponent extends AndroidInjector<MainActivity> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<MainActivity> {
        }
    }



    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity, MainFragment.newInstance())
                    .commitNow();
        }

        appSingleton.doSomething();
        activitySingleton.doSomething();
    }
}
