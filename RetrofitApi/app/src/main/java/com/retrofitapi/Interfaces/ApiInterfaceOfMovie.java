package com.retrofitapi.Interfaces;

import com.retrofitapi.Retrofit.PojoClass.MovieDetails;
import com.retrofitapi.Retrofit.PojoClass.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created on 30-01-2018.
 */

public interface ApiInterfaceOfMovie {

    //URL: http://api.themoviedb.org/3/movie/top_rated?api_key=c031081641056bf9fe906409b8730cde
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    //URL: http://api.themoviedb.org/3/movie/{id}?api_key=c031081641056bf9fe906409b8730cde
    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
