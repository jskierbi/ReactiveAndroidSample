package com.jskierbi.lifecycle_component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jakub on 05/26/2015.
 */
public class LifecycleService implements LifecycleCallbacks {

	protected LifecycleService(Activity activity, String tag) {
		LifecycleCallbackFragment.register(activity.getFragmentManager(), tag, this);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {}
	@Override public void onDestroy() {}
	@Override public void onStart() {}
	@Override public void onStop() {}
	@Override public void onResume() {}
	@Override public void onPause() {}
	@Override public void onSaveInstanceState(Bundle outState) {}
	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {}
}
