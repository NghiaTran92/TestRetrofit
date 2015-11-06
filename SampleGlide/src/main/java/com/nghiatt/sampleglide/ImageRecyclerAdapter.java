package com.nghiatt.sampleglide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

  private List<Result> mResultList;
  private LayoutInflater mInflater;
  private Context mContext;

  public ImageRecyclerAdapter(Context context, List<Result> list) {
    mResultList = list;
    mContext = context;
    mInflater = LayoutInflater.from(mContext);
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_image, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    Result result = mResultList.get(position);
    if (result.getImageUrl() != null) {
      Glide.with(mContext)
          .load(result.getImageUrl())
          // get full image
          .diskCacheStrategy(DiskCacheStrategy.ALL)
          .centerCrop()
          .into(holder.imgSearch);
    }
  }

  @Override public int getItemCount() {
    return mResultList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgSearch;

    public ViewHolder(View view) {
      super(view);
      imgSearch = (ImageView) view.findViewById(R.id.img_search);
    }
  }
}
