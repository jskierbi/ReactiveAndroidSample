package com.jskierbi.reactiveandroidsample.ui.event_driven_retry;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.jskierbi.reactiveandroidsample.App;
import com.jskierbi.reactiveandroidsample.Network.GitHubService;
import com.jskierbi.reactiveandroidsample.Network.Repository;
import com.jskierbi.reactiveandroidsample.R;
import com.jskierbi.reactiveandroidsample.dagger.ActivityModule;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 05/27/2015.
 */
public class RetryActivity extends Activity {

	private BehaviorSubject<List<Repository>> mRepositoryDataBus = BehaviorSubject.create();

	@InjectView(R.id.recyclerview) RecyclerView mRecyclerView;
	@InjectView(R.id.label_status) TextView mLabelStatus;

	@Inject GitHubService mGitHubService;
	@Inject CompositeSubscription mUiSubscriptions;
	@Inject CompositeSubscription mDataSubscriptions;

	private List<Repository> mRepositoryList = new ArrayList<>();

	@Override protected void onCreate(Bundle savedInstanceState) {
		App.from(this)
						.getAppComponent()
						.plusActivityComponent(new ActivityModule(this))
						.inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retry);
		ButterKnife.inject(this);

		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mAdapter);

		initRequest(mRepositoryDataBus);
	}

	@Override protected void onDestroy() {
		mDataSubscriptions.clear();
		super.onDestroy();
	}

	@Override protected void onStart() {
		performUpdateDataWhen(mRepositoryDataBus);
		super.onStart();
	}

	@Override protected void onStop() {
		mUiSubscriptions.clear();
		super.onStop();
	}

	void initRequest(final BehaviorSubject<List<Repository>> publishOn) {

		mLabelStatus.setText("Loading...");
		Subscription s = mGitHubService.rxGetAllRepositories("1")
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(new Subscriber<List<Repository>>() {
							@Override public void onCompleted() {

							}
							@Override public void onError(Throwable e) {
								// Show error
								mLabelStatus.setText("ERROR!");
								new AlertDialog.Builder(RetryActivity.this)
												.setTitle("Connection error")
												.setMessage("Retry?")
												.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
													public void onClick(DialogInterface dialog, int which) {
														initRequest(publishOn);
													}
												})
												.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
													public void onClick(DialogInterface dialog, int which) {
														RetryActivity.this.finish();
													}
												})
												.setIcon(android.R.drawable.ic_dialog_alert)
												.show();
							}
							@Override public void onNext(List<Repository> repositories) {
								mRepositoryDataBus.onNext(repositories);
								mLabelStatus.setText("Done.");
							}
						});
		mDataSubscriptions.add(s);
	}

	void performUpdateDataWhen(Observable<List<Repository>> repositoryData) {
		Subscription s = repositoryData.subscribe(new Action1<List<Repository>>() {
			@Override public void call(List<Repository> repositories) {
				mRepositoryList = repositories;
				mAdapter.notifyDataSetChanged();
			}
		});
		mUiSubscriptions.add(s);
	}

	private RecyclerView.Adapter<ItemViewHolder> mAdapter = new RecyclerView.Adapter<ItemViewHolder>() {
		@Override public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
			LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
			return new ItemViewHolder(inflater.inflate(android.R.layout.simple_list_item_2, viewGroup, false));
		}
		@Override public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
			itemViewHolder.bind(mRepositoryList.get(i));
		}
		@Override public int getItemCount() {
			return mRepositoryList.size();
		}
	};

	class ItemViewHolder extends RecyclerView.ViewHolder {

		@InjectView(android.R.id.text1) TextView mText1;
		@InjectView(android.R.id.text2) TextView mText2;

		public ItemViewHolder(View itemView) {
			super(itemView);
			ButterKnife.inject(this, itemView);
		}

		public void bind(Repository repository) {
			mText1.setText(repository.getName());
			mText2.setText(repository.getUrl());
		}
	}
}
