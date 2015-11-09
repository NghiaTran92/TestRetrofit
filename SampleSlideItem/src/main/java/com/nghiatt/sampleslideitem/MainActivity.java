package com.nghiatt.sampleslideitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerViewPager mRecyclerViewPager;
    private RecyclerPagerAdapter mAdapter;
    private TextView mTxtPage;
    private Button mBtnPre;
    private Button mBtnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtPage = (TextView) findViewById(R.id.txt_page);
        mBtnPre = (Button) findViewById(R.id.btn_pre);
        mBtnPre.setOnClickListener(this);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(this);
        mRecyclerViewPager = (RecyclerViewPager) findViewById(R.id.recycler_pager);
        mRecyclerViewPager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewPager.setHasFixedSize(true);
        //fake data
        List<String> listName = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listName.add("item " + i);
        }
        mAdapter = new RecyclerPagerAdapter(this, listName);
        mRecyclerViewPager.setAdapter(mAdapter);
        mRecyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
                mTxtPage.setText(newPosition + " / " + (mAdapter.getItemCount() - 1));
            }
        });
        mRecyclerViewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int childCount = mRecyclerViewPager.getChildCount();
                int width = mRecyclerViewPager.getChildAt(0).getWidth();
                int padding = (mRecyclerViewPager.getWidth() - width) / 2;
                Log.d("RecyclerPager", "childCount=" + childCount + "--width=" + width + "--padding=" + padding);
                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    float rate = 0;
                    Log.d("RecyclerPager", "getLeft=" + v.getLeft() + " -- item=" + j);
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        Log.d("RecyclerPager", "rate=" + rate);
                        v.setPivotY(0);
                        v.setScaleY(1 - rate * 0.1f);
//                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        Log.d("RecyclerPager", "rate=" + rate);
                        v.setPivotY(0);
                        v.setScaleY(0.9f + rate * 0.1f);
//                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        mRecyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerViewPager.getChildCount() < 3) {
                    if (mRecyclerViewPager.getChildAt(1) != null) {
                        if (mRecyclerViewPager.getCurrentPosition() == 0) {
                            View v1 = mRecyclerViewPager.getChildAt(1);
                            v1.setPivotY(0);
                            v1.setScaleY(0.9f);
//                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = mRecyclerViewPager.getChildAt(0);
                            v1.setPivotY(0);
                            v1.setScaleY(0.9f);
//                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (mRecyclerViewPager.getChildAt(0) != null) {
                        View v0 = mRecyclerViewPager.getChildAt(0);
                        v0.setPivotY(0);
                        v0.setScaleY(0.9f);
//                        v0.setScaleX(0.9f);
                    }
                    if (mRecyclerViewPager.getChildAt(2) != null) {
                        View v2 = mRecyclerViewPager.getChildAt(2);
                        v2.setPivotY(0);
                        v2.setScaleY(0.9f);
//                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int currentPositionPager = mRecyclerViewPager.getCurrentPosition();
        switch (id) {
            case R.id.btn_pre:
                if (currentPositionPager > 0) {
                    mRecyclerViewPager.smoothScrollToPosition(currentPositionPager - 1);
                }
                break;
            case R.id.btn_next:
                if (currentPositionPager < mAdapter.getItemCount() - 1) {
                    mRecyclerViewPager.smoothScrollToPosition(currentPositionPager + 1);
                }
                break;
        }
    }
}
