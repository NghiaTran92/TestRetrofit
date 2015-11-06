package com.nghiatt.sampleglide;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResponseData {
  @SerializedName("results") private List<Result> resultList;

  public List<Result> getResultList() {
    return resultList == null ? (resultList = new ArrayList<>()) : resultList;
  }

  public void setResultList(List<Result> resultList) {
    this.resultList = resultList;
  }
}
