package com.aresrd.android.beatrp;

import retrofit.RestAdapter;

/**
 * Created by Ramadan on 25.12.2016.
 */

public class RestService {
    private static final String URL = "http://localhost";
    private retrofit.RestAdapter restAdapter;
    private IService apiService;

    public RestService()
    {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        apiService = restAdapter.create(IService.class);
    }

    public IService getService()
    {
        return apiService;
    }
}