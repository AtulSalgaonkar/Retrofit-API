package com.retrofitapi.Singleton;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 30-01-2018.
 */

public class ApiClientOfMovie {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit mRetrofit = null;

    private ApiClientOfMovie() {

    }

    public static Retrofit getRetrofitInstanceOfMovieDb() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

}
