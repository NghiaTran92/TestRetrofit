package com.nghiatt.view.status;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nghiatt.view.status.childview.BaseStatusLayout;


/**
 * Created by Ngh on 11/8/2015.
 */
public class StatusLayout extends RelativeLayout {
    private static final String TAG = "StatusLayout";

    private RelativeLayout mGroupStatus;
    private BaseStatusLayout mBaseStatusLayout;
    private Activity mActivity;
    private LayoutInflater mInflater;

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        mGroupStatus = new RelativeLayout(this.getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mGroupStatus.setLayoutParams(layoutParams);
        mGroupStatus.setBackgroundColor(Color.WHITE);
        addView(mGroupStatus);
        // default hidden
        hiddenStatus();
        if (mBaseStatusLayout != null) {
            initStatus();
        }
    }

    private void initStatus() {
        Log.i(TAG, "initStatus");
        if (mActivity != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    removeAllStatus();
                    View view = null;
                    try {
                        view = mInflater.inflate(mBaseStatusLayout.getLayoutID(), mGroupStatus, false);
                    } catch (InflateException ie) {
                        Log.e(TAG, ie.getMessage());
                    }

                    if (view != null) {
                        ViewGroup.LayoutParams groupStatusLayoutParam = mGroupStatus.getLayoutParams();
                        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(groupStatusLayoutParam.width, groupStatusLayoutParam.height);

                        view.setLayoutParams(layoutParams);
                        mGroupStatus.addView(view);
                        mBaseStatusLayout.onCreate(view);
                    }
                }
            });

        }

    }

    public void setLayout(BaseStatusLayout baseStatusLayout) {
        if (baseStatusLayout != null) {
            Log.i(TAG, "setLayout");
            if (mBaseStatusLayout == baseStatusLayout) {
                Log.d(TAG, "same object");
                return;
            }
            mActivity = baseStatusLayout.getActivity();
            mBaseStatusLayout = baseStatusLayout;
            if (mGroupStatus != null) {
                initStatus();
            }
        }
    }

    public void hiddenStatus() {
        if (mGroupStatus != null && mGroupStatus.getVisibility() != View.GONE) {
            mGroupStatus.setVisibility(View.GONE);
        }
    }

    public void removeAllStatus() {
        if (mGroupStatus.getChildCount() > 0) {
            if (mBaseStatusLayout != null) {
                mBaseStatusLayout.onDestroy();
            }
            mGroupStatus.removeAllViews();
        }
        hiddenStatus();
    }

    public void showStatus() {
        if (mGroupStatus != null) {
            mGroupStatus.setVisibility(View.VISIBLE);
        }
    }

}
