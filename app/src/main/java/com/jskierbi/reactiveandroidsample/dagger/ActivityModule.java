package com.jskierbi.reactiveandroidsample.dagger;

import android.app.Activity;
import com.jskierbi.reactiveandroidsample.ui.bus.CachedEventBus;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jakub on 25/05/15.
 */
@Module
public class ActivityModule {

  private final Activity mActivity;

  public ActivityModule(Activity mActivity) {
    this.mActivity = mActivity;
  }

  @Provides
  @ActivityScope
  @ForActivity
  public CachedEventBus provideChachedEventBus(CachedEventBus cachedEventBus) {
    return cachedEventBus;
  }
}
