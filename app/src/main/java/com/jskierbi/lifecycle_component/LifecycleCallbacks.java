package com.jskierbi.lifecycle_component;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jakub on 05/26/2015.
 */
public interface LifecycleCallbacks {

	void onActivityCreated(Bundle savedInstanceState);
	void onDestroy();
	void onStart();
	void onStop();
	void onResume();
	void onPause();
	void onSaveInstanceState(Bundle outState);
	void onActivityResult(int requestCode, int resultCode, Intent data);
}
