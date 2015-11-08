package com.example.ngh.samplelayoutstatus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.nghiatt.view.status.StatusLayout;
import com.nghiatt.view.status.childview.BaseStatusLayout;
import com.nghiatt.view.status.childview.LoadingStatus;
import com.nghiatt.view.status.childview.RetryStatus;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RadioButton mRaBtnLoading;
    private RadioButton mRaBtnHidden;
    private RadioButton mRaBtnTryAgain;
    private StatusLayout mStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.ra_group);
        mRaBtnHidden = (RadioButton) findViewById(R.id.ra_hidden);
        mRaBtnLoading = (RadioButton) findViewById(R.id.ra_loading);
        mRaBtnTryAgain = (RadioButton) findViewById(R.id.ra_try_again);
        mStatusLayout = (StatusLayout) findViewById(R.id.status_layout);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                BaseStatusLayout baseStatusLayout = null;
                if (mRaBtnLoading.isChecked()) {
                    baseStatusLayout = new LoadingStatus(MainActivity.this);
                } else if (mRaBtnTryAgain.isChecked()) {
                    baseStatusLayout = new RetryStatus(MainActivity.this);
                } else if (mRaBtnHidden.isChecked()) {
                    mStatusLayout.hiddenStatus();
                }
                if (baseStatusLayout != null) {
                    mStatusLayout.setLayout(baseStatusLayout);
                    mStatusLayout.showStatus();
                }
            }
        });
    }
}
