package com.jskierbi.reactiveandroidsample.ui.bus;

import android.util.Log;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Cached event bus, hooray!
 * <p/>
 * Created by jakub on 25/05/15.
 */
public class CachedEventBus {

  private static final String TAG = CachedEventBus.class.getSimpleName();

  private Map<Class, Subject<Object, Object>> mSubjectMap = new HashMap<>();

  @Inject
  public CachedEventBus() {
  }

  public void post(Object object) {
    getOrCreate(object.getClass()).onNext(object);
  }

  public void clearCached() {
    for (Subject<Object, Object> s : mSubjectMap.values()) {
      s.onNext(null); // Clear all cached values
    }
  }

  public <T> Observable<T> eventsFor(Class<T> clazz) {
    return getOrCreate(clazz)
        .filter(new Func1<Object, Boolean>() {
          @Override
          public Boolean call(Object o) {
            return o != null;
          }
        })
        .map(new Func1<Object, T>() {
          @Override
          public T call(Object o) {
            return (T) o;
          }
        });
  }

  public void debugDumpCached() {
    Observable
        .from(mSubjectMap.values())
        .flatMap(new Func1<Subject<Object, Object>, Observable<?>>() {
          @Override
          public Observable<Object> call(Subject<Object, Object> subject) {
            return subject;
          }
        })
        .subscribe(new Action1<Object>() {
          @Override
          public void call(Object o) {
            Log.d(TAG, "[" + o.getClass().getSimpleName() + "]=" + o);
          }
        });
  }

  private Subject<Object, Object> getOrCreate(Class clazz) {
    Subject<Object, Object> subject;
    if ((subject = mSubjectMap.get(clazz)) == null) {
      subject = new SerializedSubject<>(BehaviorSubject.create());
      mSubjectMap.put(clazz, subject);
    }
    return subject;
  }
}
