package com.akhilamadari.android.searchjobs.Retrofit;

public class ApiUtils {

    private static final String BASE_URL = "https://jobs.github.com/";

    public static RestInterface getServiceClass(){
        return RestClient.getRetrofit(BASE_URL).create(RestInterface.class);
    }
}
