package com.nghiatt.sampleglide;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface ApiService {

  // example: https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=taylor&rsz=8&start=8
  @GET("/ajax/services/search/images") Call<Data> getData(@Query("v") String version,
      @Query("q") String query,@Query("rsz") int size, @Query("start") int startOffset) ;
}
