package com.retrofitapi.screen_one;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retrofitapi.utils.HelperMethods;
import com.retrofitapi.R;
import com.retrofitapi.adapter.MyRvAdapter;
import com.retrofitapi.service.ApiClientOfMovie;
import com.retrofitapi.service.ApiInterfaceOfMovie;
import com.retrofitapi.service.model.Movie;
import com.retrofitapi.service.model.MovieResponse;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 30-01-2018.
 */

public class Fragment1_Rv extends Fragment {
    Context mContext;
    RecyclerView mRecyclerView;
    ApiInterfaceOfMovie mApiInterfaceOfMovie;
    private MyRvAdapter mRecyclerViewAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> mData;

    private final static String API_KEY = "c031081641056bf9fe906409b8730cde";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();

        View view = (View) inflater.inflate(R.layout.fragment1_layout, container, false);

        if (view != null) {
            mRecyclerView = view.findViewById(R.id.reyclerview_display_data);

            setRelativeData();

            //mRecyclerViewAdapter = new MyRvAdapter(getData());
            //mRecyclerView.setAdapter(mRecyclerViewAdapter);


        }

        return view;
    }

    private void setRelativeData() {

        mApiInterfaceOfMovie = ApiClientOfMovie.getRetrofitInstanceOfMovieDb().create(ApiInterfaceOfMovie.class);

        retrofit2.Call<MovieResponse> call = mApiInterfaceOfMovie.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(retrofit2.Call<MovieResponse> call, Response<MovieResponse> response) {
                int statusCode = response.code();
                mData = response.body().getResults();

                mLayoutManager = new LinearLayoutManager(mContext);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());

                mRecyclerView.setAdapter(new MyRvAdapter(mData, mContext));
            }

            @Override
            public void onFailure(retrofit2.Call<MovieResponse> call, Throwable t) {
                HelperMethods.showToastLong(mContext, "Error: " + t.getMessage());
            }
        });
    }


}
