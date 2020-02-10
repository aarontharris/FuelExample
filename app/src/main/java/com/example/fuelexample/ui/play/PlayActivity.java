package com.example.fuelexample.ui.play;

import android.os.Bundle;

import com.example.fuelexample.R;
import com.example.fuelexample.app.AppSingleton;
import com.example.fuelexample.core.di.ActivityScope;
import com.example.fuelexample.core.os.CoreActivity;
import com.example.fuelexample.ui.play.fragment.play.PlayFragment;
import com.example.fuelexample.ui.play.fragment.play.PlayFragment.PlayFragmentModule;
import com.example.fuelexample.ui.singleton.ActivitySingleton;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

public class PlayActivity extends CoreActivity {

    @Module(subcomponents = PlayComponent.class)
    public abstract class PlayModule {

        @Binds
        @IntoMap
        @ClassKey(PlayActivity.class)
        abstract AndroidInjector.Factory<?> bindMainAndroidInjectorFactory(PlayComponent.Factory factory);

    /*

    // TODO:

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
    @Subcomponent(modules = PlayFragmentModule.class)
    public interface PlayComponent extends AndroidInjector<PlayActivity> {
        @Subcomponent.Factory
        public interface Factory extends AndroidInjector.Factory<PlayActivity> {
        }
    }

    @Inject AppSingleton appSingleton;
    @Inject ActivitySingleton activitySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.play_activity, PlayFragment.newInstance())
                    .commitNow();
        }

        appSingleton.doSomething();
        activitySingleton.doSomething();
    }
}
