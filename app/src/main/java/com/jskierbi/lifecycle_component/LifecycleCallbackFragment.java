package com.jskierbi.lifecycle_component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jakub on 05/26/2015.
 */
public class LifecycleCallbackFragment extends Fragment {

	private LifecycleCallbacks mCallbacks;

	public static LifecycleCallbackFragment register(FragmentManager fragmentManager,
	                                                 String tag,
	                                                 LifecycleCallbacks callbacks) {
		LifecycleCallbackFragment fragment = (LifecycleCallbackFragment) fragmentManager.findFragmentByTag(tag);
		if (fragment == null) {
			fragment = new LifecycleCallbackFragment();
			fragmentManager.beginTransaction()
					.add(fragment, tag)
					.commit();
		}
		fragment.mCallbacks = callbacks;
		return fragment;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mCallbacks != null) mCallbacks.onActivityCreated(savedInstanceState);
	}

	@Override public void onDestroy() {
		super.onDestroy();
		if (mCallbacks != null) mCallbacks.onDestroy();
	}

	@Override public void onStart() {
		super.onStart();
		if (mCallbacks != null) mCallbacks.onStart();
	}

	@Override public void onStop() {
		super.onStop();
		if (mCallbacks != null) mCallbacks.onStop();
	}

	@Override public void onResume() {
		super.onResume();
		if (mCallbacks != null) mCallbacks.onResume();
	}

	@Override public void onPause() {
		super.onPause();
		if (mCallbacks != null) mCallbacks.onPause();
	}

	@Override public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mCallbacks != null) mCallbacks.onSaveInstanceState(outState);
	}

	@Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (mCallbacks != null) mCallbacks.onActivityResult(requestCode, resultCode, data);
	}
}
