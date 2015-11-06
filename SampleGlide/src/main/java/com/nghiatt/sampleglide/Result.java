package com.nghiatt.sampleglide;

import com.google.gson.annotations.SerializedName;

public class Result {
  @SerializedName("url")
  private String mImageUrl;

  public Result() {
  }

  public Result(String mImageUrl) {
    this.mImageUrl = mImageUrl;
  }

  public String getImageUrl() {
    return mImageUrl;
  }

  public void setImageUrl(String mImageUrl) {
    this.mImageUrl = mImageUrl;
  }
}
