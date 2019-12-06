package com.example.utils;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.ngyb.utils.AppUtils;
import com.ngyb.utils.DateUtils;
import com.ngyb.utils.PhoneUtils;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppUtils appUtils = new AppUtils();
//        int runningProcess2 = appUtils.getRunningProcess2(this);
//        Log.e(TAG, "onCreate: " + runningProcess2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 7219);
        }
        PhoneUtils phoneUtils = new PhoneUtils(this);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.e(TAG, "onCreate: " + phoneUtils.getNativePhoneNumber());
//        DateUtils dateUtils = new DateUtils();
//        Log.e(TAG, "onCreate: " + dateUtils.getCurYear() + "==" + dateUtils.getCurMonth() + "===" + dateUtils.getCurDay());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhoneUtils phoneUtils = new PhoneUtils(this);
        Log.e(TAG, "onCreate: " + phoneUtils.getNativePhoneNumber());
    }
}
