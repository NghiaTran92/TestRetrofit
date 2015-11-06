package com.nghiatt.sampleglide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Callback<Data> {

  private RecyclerView mRecyclerView;
  private ImageRecyclerAdapter mAdapter;
  private ResponseData responseData;
  private List<Result> mResultList;
  private Call<Data> mCall;
  private int mOffset = 0;
  private ApiService mApiService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mRecyclerView = (RecyclerView) findViewById(R.id.list_image);
    mRecyclerView.setLayoutManager(
        new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
    mResultList = new ArrayList<>();
    mAdapter = new ImageRecyclerAdapter(this, mResultList);
    mRecyclerView.setAdapter(mAdapter);

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ajax.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    mApiService = retrofit.create(ApiService.class);
    mCall = mApiService.getData("1.0", "taylor", 8, mOffset);
    mCall.enqueue(this);
  }

  private void cloneResquest(Call<Data> call) {
    Call<Data> callClone = call.clone();
    callClone.enqueue(this);
  }

  @Override public void onResponse(Response<Data> response, Retrofit retrofit) {
    Data data = response.body();
    if (data != null && data.mResponseData != null) {
      List<Result> resultList = data.mResponseData.getResultList();
      if (resultList != null && resultList.size() > 0) {
        mResultList.addAll(resultList);
        mAdapter.notifyDataSetChanged();
        // demo load more
        if (mOffset <= 0) {
          mOffset+=8;
          mCall= mApiService.getData("1.0","marria ozawa",8,mOffset);
          mCall.enqueue(this);
        }
        ////////////////////
      }
    } else {
      try {
        Log.e("error onResponse", "message:" + response.errorBody().string());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public void onFailure(Throwable t) {

  }
}
