package com.akhilamadari.android.searchjobs.Retrofit;

public class ApiUtils {


// Base Url
    private static final String BASE_URL = "https://jobs.github.com/";
// creating the interface
    public static RestInterface getServiceClass(){
        return RestClient.getRetrofit(BASE_URL).create(RestInterface.class);
    }
}
