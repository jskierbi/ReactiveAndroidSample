package com.jskierbi.reactiveandroidsample.dagger;

import com.jskierbi.reactiveandroidsample.App;
import dagger.Component;

/**
 * Created by jakub on 25/05/15.
 */
@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {

	ActivityComponent plusActivityComponent(ActivityModule activityModule);

	void inject(App app);
}
