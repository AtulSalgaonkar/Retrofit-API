package com.retrofitapi.RecyclerviewSetup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofitapi.Interfaces.OnMovieSelected;
import com.retrofitapi.Required.HelperMethods;
import com.retrofitapi.R;
import com.retrofitapi.Retrofit.PojoClass.Movie;
import com.retrofitapi.Singleton.PreferenceHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created on 30-01-2018.
 */

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {

    private List<Movie> mMyModelArrayList;
    private Context mContext;
    private static final String mPath = "https://image.tmdb.org/t/p/w500/";
    private static OnMovieSelected mOnMovieSelected;
    private PreferenceHelper mPreferenceHelper;
    private static final String mMovieIdKey = "MOVIE_ID_KEY";

    public MyRvAdapter(List<Movie> myModelArrayList, Context context) {
        this.mMyModelArrayList = myModelArrayList;
        mContext = context;
        mPreferenceHelper = PreferenceHelper.getInstance(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();

        View view = (View) LayoutInflater.from(mContext)
                .inflate(R.layout.recyclerview_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Movie movie = mMyModelArrayList.get(position);

        holder.mTitle.setText(movie.getTitle());
        holder.mSubtitle.setText(movie.getOriginalTitle());
        holder.mDescription.setText(movie.getOverview());
        Picasso.with(mContext).load(mPath + movie.getPosterPath()).into(holder.mRatingImage);

        View childView = holder.itemView;

        if (childView != null) {
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPreferenceHelper.clearAllData();
                    mPreferenceHelper.setInt(mMovieIdKey, movie.getId());

                    mOnMovieSelected.onMovieSelected(true);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mMyModelArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mSubtitle, mDescription;
        ImageView mRatingImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            mRatingImage = (ImageView) itemView.findViewById(R.id.rating_image_of_rv);

            mTitle = (TextView) itemView.findViewById(R.id.title_of_rv);
            mSubtitle = (TextView) itemView.findViewById(R.id.subtitle_of_rv);
            mDescription = (TextView) itemView.findViewById(R.id.description_of_rv);
        }
    }

    public static void setOnMovieSelected(OnMovieSelected onMovieSelected) {
        mOnMovieSelected = onMovieSelected;
    }

}
