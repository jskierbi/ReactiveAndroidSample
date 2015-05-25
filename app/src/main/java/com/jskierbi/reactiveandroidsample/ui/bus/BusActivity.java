package com.jskierbi.reactiveandroidsample.ui.bus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jskierbi.reactiveandroidsample.App;
import com.jskierbi.reactiveandroidsample.R;
import com.jskierbi.reactiveandroidsample.dagger.ActivityComponent;
import com.jskierbi.reactiveandroidsample.dagger.ActivityModule;
import com.jskierbi.reactiveandroidsample.dagger.ForActivity;

import javax.inject.Inject;

/**
 * Created by jakub on 25/05/15.
 */
public class BusActivity extends AppCompatActivity {

  private static final String TAG = BusActivity.class.getSimpleName();
  private ActivityComponent mActivityComponent;

  @Inject
  @ForActivity
  CachedEventBus mCachedEventBus;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    mActivityComponent = App.from(this)
        .getAppComponent()
        .plusActivityComponent(new ActivityModule(this));
    mActivityComponent.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bus);
    ButterKnife.inject(this);
  }

  public ActivityComponent getComponent() {
    return mActivityComponent;
  }

  @OnClick(R.id.btn_produce)
  void produceClick() {
    Log.d(TAG, "event bus: " + mCachedEventBus);
  }
}
