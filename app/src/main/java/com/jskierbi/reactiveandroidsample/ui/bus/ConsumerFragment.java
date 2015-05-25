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
import rx.Subscription;
import rx.functions.Action1;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ConsumerFragment extends Fragment {

  private static final String TAG = ConsumerFragment.class.getSimpleName();

  @Inject
  @ForActivity
  CachedEventBus mBus;

  private List<Subscription> mSubscriptions = new ArrayList<>();

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

  @OnClick(R.id.btn_register_str)
  void registerClick() {
    mSubscriptions.add(
        mBus.eventsFor(String.class).subscribe(onStringEvent)
    );
  }

  @OnClick(R.id.btn_register_publishable)
  void regiseterPublishableStr() {
    mSubscriptions.add(
        mBus.eventsFor(PublishableData.class).subscribe(onPublishableData)
    );
  }

  @OnClick(R.id.btn_unregister)
  void unregisterClick() {
    for (Subscription s : mSubscriptions) {
      s.unsubscribe();
    }
  }

  @OnClick(R.id.btn_debug_dump_cached)
  void debugDumpCachedClick() {
    
  }

  private Action1<String> onStringEvent = new Action1<String>() {
    @Override
    public void call(String string) {
      Log.d(TAG, string);
    }
  };

  private Action1<PublishableData> onPublishableData = new Action1<PublishableData>() {
    @Override
    public void call(PublishableData publishableData) {
      Log.d(TAG, "Published data: " + publishableData);
    }
  };
}
