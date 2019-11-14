package com.example.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ngyb.utils.AppUtils;
import com.ngyb.utils.DateUtils;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppUtils appUtils = new AppUtils();
//        int runningProcess2 = appUtils.getRunningProcess2(this);
//        Log.e(TAG, "onCreate: " + runningProcess2);
        DateUtils dateUtils = new DateUtils();
        Log.e(TAG, "onCreate: " + dateUtils.getCurYear() + "==" + dateUtils.getCurMonth() + "===" + dateUtils.getCurDay());
    }
}
