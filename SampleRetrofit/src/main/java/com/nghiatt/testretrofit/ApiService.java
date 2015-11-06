package com.nghiatt.testretrofit;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {

  // example: http://api.openweathermap.org/data/2.5/weather?q=London&appid=2de143494c0b295cca9337e1e96b00e0
  @GET("/data/2.5/weather") Call<Weather> getWeather(
      @Query("q") String name, @Query("appid") String appid) ;
}
