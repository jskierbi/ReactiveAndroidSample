package com.jskierbi.reactiveandroidsample.Network;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

import java.util.List;

/**
 * Created by jakub on 15/05/15.
 */
public interface GitHubService {

  @Headers("User-Agent: awestome-android-rx-test")
  @GET("/repositories")
  public List<Repository> getAllRepositories(@Query("since") String since);

  @Headers("User-Agent: awestome-android-rx-test")
  @GET("/repositories")
  public Observable<List<Repository>> rxGetAllRepositories(@Query("since") String since);
}
