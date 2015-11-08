package com.nghiatt.view.status.childview;

import android.app.Activity;
import android.view.View;

/**
 * Created by Ngh on 11/8/2015.
 */
public abstract class BaseStatusLayout {

    protected Activity mActivity;

    public BaseStatusLayout(Activity activity) {
        mActivity = activity;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public abstract int getLayoutID();

    public abstract void onCreate(View rootView);

    public abstract void onDestroy();

}
