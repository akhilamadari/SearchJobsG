package com.akhilamadari.android.searchjobs.Retrofit;

import com.akhilamadari.android.searchjobs.Models.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RestInterface {

    @GET("/positions.json?")
    public Call<List<Job>> getJobListings(@Query("description") String description,
                                          @Query("location") String location,
                                          @Query("full_time") boolean isFullTime);

}
