package io.mochadwi.presentation;

import io.mochadwi.presentation.internal.di.components.ApplicationComponent;
import io.mochadwi.presentation.internal.di.components.DaggerApplicationComponent;
import io.mochadwi.presentation.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .build();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
