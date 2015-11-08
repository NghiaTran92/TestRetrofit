package com.nghiatt.view.status.childview;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ngh.samplelayoutstatus.R;

/**
 * Created by Ngh on 11/9/2015.
 */
public class RetryStatus extends BaseStatusLayout {
    public RetryStatus(Activity activity) {
        super(activity);
    }

    @Override
    public int getLayoutID() {
        return R.layout.layout_retry;
    }

    @Override
    public void onCreate(View rootView) {
        Button btnRetry = (Button) rootView.findViewById(R.id.btn_try_again);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.again, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {

    }
}
