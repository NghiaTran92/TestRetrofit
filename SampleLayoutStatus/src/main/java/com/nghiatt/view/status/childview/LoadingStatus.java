package com.nghiatt.view.status.childview;

import android.app.Activity;
import android.view.View;

import com.example.ngh.samplelayoutstatus.R;

/**
 * Created by Ngh on 11/9/2015.
 */
public class LoadingStatus extends BaseStatusLayout {
    public LoadingStatus(Activity activity) {
        super(activity);
    }

    @Override
    public int getLayoutID() {
        return R.layout.layout_loading;
    }

    @Override
    public void onCreate(View rootView) {

    }

    @Override
    public void onDestroy() {

    }
}
