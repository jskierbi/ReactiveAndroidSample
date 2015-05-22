package com.jskierbi.reactiveandroidsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.jskierbi.reactiveandroidsample.Network.Repository;
import com.jskierbi.reactiveandroidsample.Network.Services;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

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
            })
        ;
    }
}
