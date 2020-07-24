package com.example.appliccation01.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("weather?appid=97ad61ecbf240b301fbb872b7f679120&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);


}
