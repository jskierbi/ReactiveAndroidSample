package com.jskierbi.reactiveandroidsample.dagger;

import com.jskierbi.reactiveandroidsample.App;
import dagger.Module;

/**
 * Created by jakub on 25/05/15.
 */
@Module
public class AppModule {

  private final App mApp;

  public AppModule(App mApp) {
    this.mApp = mApp;
  }
}
