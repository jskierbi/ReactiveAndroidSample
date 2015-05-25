package com.jskierbi.reactiveandroidsample.dagger;

import com.jskierbi.reactiveandroidsample.ui.bus.BusActivity;
import com.jskierbi.reactiveandroidsample.ui.bus.ConsumerFragment;
import dagger.Subcomponent;

/**
 * Created by jakub on 25/05/15.ł
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

  void inject(BusActivity busActivity);

  void inject(ConsumerFragment consumerFragment);
}
