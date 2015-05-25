package com.jskierbi.reactiveandroidsample.ui.bus;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jskierbi.reactiveandroidsample.R;
import com.jskierbi.reactiveandroidsample.dagger.ForActivity;

import javax.inject.Inject;

public class ConsumerFragment extends Fragment {

  private static final String TAG = ConsumerFragment.class.getSimpleName();

  @Inject
  @ForActivity
  CachedEventBus mCachedEventBus;

  @Override
  public void onAttach(Activity activity) {
    ((BusActivity) getActivity()).getComponent().inject(this);
    super.onAttach(activity);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_consumer, container, false);
    ButterKnife.inject(this, v);
    return v;
  }

  @OnClick(R.id.btn_register)
  void registerClick() {
    Log.d(TAG, "Event bus: " + mCachedEventBus);
  }

  @OnClick(R.id.btn_unregister)
  void unregisterClick() {

  }
}
