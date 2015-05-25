package com.jskierbi.reactiveandroidsample.dagger;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jakub on 25/05/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface ForActivity {
}
