package com.jskierbi.reactiveandroidsample.ui.lifecycle_component;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jskierbi.lifecycle_component.GoogleApiClientManager;
import com.jskierbi.reactiveandroidsample.App;
import com.jskierbi.reactiveandroidsample.R;
import com.jskierbi.reactiveandroidsample.dagger.ActivityComponent;
import com.jskierbi.reactiveandroidsample.dagger.ActivityModule;

import javax.inject.Inject;

/**
 * Created by jakub on 25/05/15.
 */
public class LifecycleComponentActivity extends AppCompatActivity {

	private static final String TAG = LifecycleComponentActivity.class.getSimpleName();

	private ActivityComponent mActivityComponent;

	@Inject
	GoogleApiClientManager mGoogleApiClientManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mActivityComponent = App.from(this)
						.getAppComponent()
						.plusActivityComponent(new ActivityModule(this));
		mActivityComponent.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycle_component);
		ButterKnife.inject(this);
	}

	@OnClick(R.id.btn_start_activity_for_result) void startActivityForResultClick() {

	}
}
