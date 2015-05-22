package com.jskierbi.reactiveandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jskierbi.reactiveandroidsample.Network.CacheMissException;
import com.jskierbi.reactiveandroidsample.Network.Repository;
import com.jskierbi.reactiveandroidsample.Network.Services;
import hugo.weaving.DebugLog;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  private String mMyString;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.btn_rxtest_1)
  void rxText1Click() {

    Observable<String> observable = Observable.just("Hello world");
    observable
        .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            Log.d(TAG, "Action1() " + s);
          }
        });

    Services.GITHUB.rxGetAllRepositories("1")
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap(new Func1<List<Repository>, Observable<Repository>>() {
          @Override
          public Observable<Repository> call(List<Repository> repositories) {
            return Observable.from(repositories);
          }
        })
        .subscribe(new Action1<Repository>() {
          @Override
          public void call(Repository repository) {
            Log.d(TAG, repository.toString());
          }
        });
  }

  @OnClick(R.id.btn_grokking_rxjava_pt1)
  void onGrokkingPt1Click() {

    Observable<String> myObservable = Observable.create(
        new Observable.OnSubscribe<String>() {
          @Override
          public void call(Subscriber<? super String> sub) {
            sub.onNext("Hello, world!");
            sub.onNext("This is next item emitted!");
            sub.onCompleted();
          }
        }
    );

    Subscriber<String> mySubscriber = new Subscriber<String>() {
      @Override
      public void onNext(String s) {
        Log.d(TAG, "Got item: " + s);
      }

      @Override
      public void onCompleted() {
      }

      @Override
      public void onError(Throwable e) {
      }
    };

    myObservable.subscribe(mySubscriber);

    Observable
        .from(new String[]{"one", "two"})
        .subscribe(new Action1<String>() {
          @Override
          public void call(String s) {
            Log.d(TAG, "Action1: " + s);
          }
        });

    Observable
        .just(new String[]{"ten", "eleven"})
        .subscribe(new Action1<String[]>() {
          @Override
          public void call(String[] strings) {

          }
        });

    Observable
        .just(new String[]{"ten", "eleven"})
        .subscribe(new Action1<String[]>() {
          @Override
          public void call(String[] strings) {

          }
        });

    // map
    // flatMap
    // filter
    // take
    // skip
    // skipLast
  }

  @OnClick(R.id.btn_producer_consumer)
  void producerConsumerClick() {

    Observable
        .merge(
            Observable.create(new Observable.OnSubscribe<String>() {
              @Override
              public void call(Subscriber<? super String> subscriber) {
                try {
                  Thread.sleep(50);
                  subscriber.onNext("one");
                  Thread.sleep(100);
                  subscriber.onNext("two");
                  Thread.sleep(100);
                  subscriber.onNext("three");
                } catch (InterruptedException ignore) {
                }
              }
            }).subscribeOn(Schedulers.io()),
            Observable.create(new Observable.OnSubscribe<String>() {
              @Override
              public void call(Subscriber<? super String> subscriber) {
                try {
                  subscriber.onNext("jeden");
                  Thread.sleep(100);
                  subscriber.onNext("dwa");
                  Thread.sleep(100);
                  subscriber.onNext("trzy");
                } catch (InterruptedException ignore) {
                }
              }
            }).subscribeOn(Schedulers.io())
        )
        .subscribe(new Subscriber<String>() {
          @Override
          @DebugLog
          public void onCompleted() {

          }

          @Override
          @DebugLog
          public void onError(Throwable e) {

          }

          @Override
          @DebugLog
          public void onNext(String s) {

          }
        })
    ;
  }

  @OnClick(R.id.btn_cache_strategy)
  void cacheStrategyClick() {

    Observable
        .create(new Observable.OnSubscribe<String>() {
          @Override
          public void call(Subscriber<? super String> subscriber) {
            subscriber.onError(new CacheMissException("Missed cache"));
          }
        })
        .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
          @Override
          public Observable<? extends String> call(Throwable throwable) {
            return Observable.just("Oh, got data from network");
          }
        });

    // Add new observable to stream
    // Return an observable from stream

    // Implement job queue as a stream

    // Cache X responses

    //
  }
}
