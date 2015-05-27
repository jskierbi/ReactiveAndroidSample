package com.jskierbi.reactiveandroidsample.dagger;

import com.jskierbi.reactiveandroidsample.App;
import com.jskierbi.reactiveandroidsample.Network.GitHubService;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jakub on 25/05/15.
 */
@Module
public class AppModule {

	private final App mApp;

	public AppModule(App mApp) {
		this.mApp = mApp;
	}

	@Provides
	@ApplicationScope
	public GitHubService provideGitHubService(RestAdapter adapter) {
		return adapter.create(GitHubService.class);
	}

	@Provides
	@ApplicationScope
	public RestAdapter provideRestAdapter() {
		return new RestAdapter.Builder()
						.setLogLevel(RestAdapter.LogLevel.FULL)
						.setEndpoint("https://api.github.com")
						.build();
	}

	@Provides
	public CompositeSubscription provideCompositeSubscription() {
		return new CompositeSubscription();
	}
}
