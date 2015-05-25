package com.jskierbi.reactiveandroidsample;

import android.app.Application;
import android.content.Context;
import com.jskierbi.reactiveandroidsample.dagger.AppComponent;
import com.jskierbi.reactiveandroidsample.dagger.AppModule;
import com.jskierbi.reactiveandroidsample.dagger.DaggerAppComponent;

/**
 * Created by jakub on 25/05/15.
 */
public class App extends Application {

  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    super.onCreate();
  }

  public static App from(Context context) {
    return (App) context.getApplicationContext();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }
}
