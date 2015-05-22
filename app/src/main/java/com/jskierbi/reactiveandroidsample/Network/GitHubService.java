/*
 * (c) Neofonie Mobile GmbH (2015)
 *
 * This computer program is the sole property of Neofonie Mobile GmbH (http://mobile.neofonie.de)
 * and is protected under the German Copyright Act (paragraph 69a UrhG).
 *
 * All rights are reserved. Making copies, duplicating, modifying, using or distributing
 * this computer program in any form, without prior written consent of Neofonie Mobile GmbH, is prohibited.
 * Violation of copyright is punishable under the German Copyright Act (paragraph 106 UrhG).
 *
 * Removing this copyright statement is also a violation.
 */
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
