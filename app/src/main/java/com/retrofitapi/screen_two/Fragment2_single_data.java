package com.retrofitapi.screen_two;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.retrofitapi.service.ApiInterfaceOfMovie;
import com.retrofitapi.R;
import com.retrofitapi.utils.HelperMethods;
import com.retrofitapi.service.model.MovieDetails;
import com.retrofitapi.service.ApiClientOfMovie;
import com.retrofitapi.utils.PreferenceHelper;
import com.squareup.picasso.Picasso;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 30-01-2018.
 */

public class Fragment2_single_data extends Fragment {

    Context mContext;
    private ImageView mMovieImg;
    private TextView mTitleTv, mOrgTitle, mDescriptionTv, mReleaseDateTv;
    private final static String API_KEY = "c031081641056bf9fe906409b8730cde";

    private PreferenceHelper mPreferenceHelper;
    private static final String mMovieIdKey = "MOVIE_ID_KEY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View view = (View) inflater.inflate(R.layout.fragment2_layout, container, false);

        if (view != null) {
            mMovieImg = (ImageView) view.findViewById(R.id.movie_img_path);
            mTitleTv = (TextView) view.findViewById(R.id.title_tv_view);
            mOrgTitle = (TextView) view.findViewById(R.id.org_title_tv_view);
            mPreferenceHelper = PreferenceHelper.getInstance(mContext);
            mDescriptionTv = (TextView) view.findViewById(R.id.description_tv_view);
            mReleaseDateTv = (TextView) view.findViewById(R.id.release_date_tv_view);

            int ID = mPreferenceHelper.getInt(mMovieIdKey, 0);

            setDetails(ID);
        }
        return view;
    }

    public void setDetails(int movieId) {

        ApiInterfaceOfMovie apiInterfaceOfMovie = ApiClientOfMovie.getRetrofitInstanceOfMovieDb().create(ApiInterfaceOfMovie.class);

        retrofit2.Call<MovieDetails> movieDetailsCall = apiInterfaceOfMovie.getMovieDetails(movieId, API_KEY);

        movieDetailsCall.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(retrofit2.Call<MovieDetails> call, Response<MovieDetails> response) {
                //int statusCode = response.code();
                MovieDetails movies = response.body();

                mTitleTv.setText(movies.getTitle());
                mOrgTitle.setText(movies.getOriginalTitle());
                mReleaseDateTv.setText(movies.getReleaseDate());
                mDescriptionTv.setText(movies.getOverview());

                Picasso.with(mContext).load("https://image.tmdb.org/t/p/w500/" + movies.getPosterPath()).into(mMovieImg);
            }

            @Override
            public void onFailure(retrofit2.Call<MovieDetails> call, Throwable t) {
                HelperMethods.showToastLong(mContext, "Error: " + t.getMessage());
            }
        });
    }
}
