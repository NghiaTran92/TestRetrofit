package com.nghiatt.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

  private TextView mTxt;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTxt=(TextView)findViewById(R.id.txt);
    final SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSSS");


    // Read: http://inthecheesefactory.com/blog/retrofit-2.0/en
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    ApiService apiService=retrofit.create(ApiService.class);
    Call<Weather> call =apiService.getWeather("London","2de143494c0b295cca9337e1e96b00e0");
    //asynchronous call
    Log.i("weather","before call:"+simpleDateFormat.format(new Date()));
    call.enqueue(new Callback<Weather>() {
      @Override public void onResponse(Response<Weather> response, Retrofit retrofit) {
        Weather weather=response.body();
        if(weather!=null){
          Log.i("weather","onResponse at "+simpleDateFormat.format(new Date())+" data:"+weather.getName()+"-- datetime:"+weather.getDateTime());
        }else{
          try {
            Log.e("error onResponse","message:"+response.errorBody().string());
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

      @Override public void onFailure(Throwable t) {
        Log.e("onFailure","message:"+t.getMessage());
      }
    });

    // synchronous call would be with execute, in this case you
    // would have to perform this outside the main thread
    // call.execute()

    // to cancel a running request
    // call.cancel();
    // calls can only be used once but you can easily clone them
    //Call<StackOverflowQuestions> c = call.clone();
    //c.enqueue(this);

  }
}
