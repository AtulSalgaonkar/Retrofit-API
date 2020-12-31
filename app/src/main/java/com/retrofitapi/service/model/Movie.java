package com.retrofitapi.service.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 30-01-2018.
 */

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("id")
    private Integer id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;


    public Movie(String posterPath, Integer id, String originalTitle, String title) {
        this.posterPath = posterPath;
        this.id = id;
        this.originalTitle = originalTitle;
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

}
