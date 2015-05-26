package com.jskierbi.lifecycle_component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.jskierbi.reactiveandroidsample.dagger.ActivityScope;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by jakub on 05/26/2015.
 */
@ActivityScope
public class GoogleApiClientManager extends LifecycleService {

	private static final String TAG = GoogleApiClientManager.class.getSimpleName();

	@Inject
	public GoogleApiClientManager(Activity activity) {
		super(activity, TAG);
	}

	@Override @DebugLog public void onActivityResult(int requestCode, int resultCode, Intent data) {
	}
	@Override @DebugLog public void onSaveInstanceState(Bundle outState) {
	}
	@Override @DebugLog public void onPause() {
	}
	@Override @DebugLog public void onResume() {
	}
	@Override @DebugLog public void onDestroy() {
	}
	@Override @DebugLog public void onActivityCreated(Bundle savedInstanceState) {
	}
	@Override @DebugLog public void onStart() {
	}
	@Override @DebugLog public void onStop() {
	}
}
