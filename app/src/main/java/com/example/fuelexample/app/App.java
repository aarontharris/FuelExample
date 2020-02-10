package com.example.fuelexample.app;

import android.app.Application;
import android.content.Context;

import com.example.fuelexample.ui.main.MainActivity.MainModule;
import com.example.fuelexample.ui.play.PlayActivity.PlayModule;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

public class App extends Application implements HasAndroidInjector {

    @Module
    public abstract class AppModule {
        @Binds abstract Context provideContext(Application application);
    }

    @Singleton
    @Component(modules = {
            AndroidSupportInjectionModule.class,
            AppModule.class,
            MainModule.class,
            PlayModule.class,
    })
    public interface AppComponent { // extends AndroidInjector<DaggerApplication> {
        void inject(App app);
    }

    @Inject DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override public void onCreate() {
        super.onCreate();
        DaggerApp_AppComponent.create().inject(this);
    }

    @Override public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
