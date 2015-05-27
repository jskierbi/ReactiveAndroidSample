package com.jskierbi.reactiveandroidsample.dagger;

import com.jskierbi.reactiveandroidsample.ui.bus.BusActivity;
import com.jskierbi.reactiveandroidsample.ui.bus.ConsumerFragment;
import com.jskierbi.reactiveandroidsample.ui.event_driven_retry.RetryActivity;
import com.jskierbi.reactiveandroidsample.ui.lifecycle_component.LifecycleComponentActivity;
import dagger.Subcomponent;

/**
 * Created by jakub on 25/05/15.ł
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

  void inject(BusActivity busActivity);

  void inject(LifecycleComponentActivity lifecycleComponentActivity);

  void inject(ConsumerFragment consumerFragment);

  void inject(RetryActivity retryActivity);
}
