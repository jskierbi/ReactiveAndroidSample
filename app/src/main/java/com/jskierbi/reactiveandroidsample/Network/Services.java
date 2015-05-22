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
