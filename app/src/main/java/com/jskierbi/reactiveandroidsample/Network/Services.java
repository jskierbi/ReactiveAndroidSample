package com.jskierbi.reactiveandroidsample.Network;

import retrofit.RestAdapter;

/**
 * Created by jakub on 15/05/15.
 */
public class Services {

  private static RestAdapter REST_ADAPTER = new RestAdapter.Builder()
      .setEndpoint("https://api.github.com")
      .setLogLevel(RestAdapter.LogLevel.FULL)
      .build();

  public static GitHubService GITHUB = REST_ADAPTER.create(GitHubService.class);
}
