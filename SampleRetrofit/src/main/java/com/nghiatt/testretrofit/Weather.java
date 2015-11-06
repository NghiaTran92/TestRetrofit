package com.nghiatt.testretrofit;

import com.google.gson.annotations.SerializedName;

public class Weather {

  @SerializedName("id") private long mId;
  @SerializedName("name") private String mName;
  @SerializedName("dt") private long mDateTime;

  public Weather() {
  }

  public Weather(long mId, String mName, long mDateTime) {
    this.mId = mId;
    this.mName = mName;
    this.mDateTime = mDateTime;
  }

  public long getId() {
    return mId;
  }

  public void setId(long mId) {
    this.mId = mId;
  }

  public String getName() {
    return mName;
  }

  public void setName(String mName) {
    this.mName = mName;
  }

  public long getDateTime() {
    return mDateTime;
  }

  public void setDateTime(long mDateTime) {
    this.mDateTime = mDateTime;
  }
}
