package com.jskierbi.lifecycle_component;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import com.jskierbi.reactiveandroidsample.dagger.ActivityScope;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by jakub on 05/26/2015.
 */
@ActivityScope
public class GoogleApiClientManager implements Application.ActivityLifecycleCallbacks {

	private static final String TAG = GoogleApiClientManager.class.getSimpleName();

	private Activity mActivity;

	@Inject
	public GoogleApiClientManager(Activity activity) {
		mActivity = activity;
		activity.getApplication().registerActivityLifecycleCallbacks(this);
	}

	public void startActivityForResult() {
		Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
		pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
		mActivity.startActivityForResult(pickContactIntent, 666);
	}

	@Override @DebugLog public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

	}
	@Override @DebugLog public void onActivityStarted(Activity activity) {

	}
	@Override @DebugLog public void onActivityResumed(Activity activity) {

	}
	@Override @DebugLog public void onActivityPaused(Activity activity) {

	}
	@Override @DebugLog public void onActivityStopped(Activity activity) {

	}
	@Override @DebugLog public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}
	@Override @DebugLog public void onActivityDestroyed(Activity activity) {
		activity.getApplication().unregisterActivityLifecycleCallbacks(this);
	}
}
