package com.nghiatt.sampleslideitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerPagerAdapter extends RecyclerView.Adapter<RecyclerPagerAdapter.ViewHolder> {

    private List<String> mListName;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerPagerAdapter(Context context, List<String> listName){
        mContext=context;
        mListName=listName;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name=mListName.get(position);
        holder.txtName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mListName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName=(TextView)itemView.findViewById(R.id.txt_name);
        }

    }
}
