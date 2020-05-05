package com.example.utils;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.ngyb.utils.AppUtils;
import com.ngyb.utils.DateUtils;
import com.ngyb.utils.PhoneUtils;
import com.ngyb.utils.StreamUtils;
import com.ngyb.utils.encryption.FrequencyAnalysis;

import java.io.File;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: 000000");
////        AppUtils appUtils = new AppUtils();
////        int runningProcess2 = appUtils.getRunningProcess2(this);
////        Log.e(TAG, "onCreate: " + runningProcess2);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 7219);
//        }
//        PhoneUtils phoneUtils = new PhoneUtils(this);
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        Log.e(TAG, "onCreate: " + phoneUtils.getNativePhoneNumber());
//        DateUtils dateUtils = new DateUtils();
//        Log.e(TAG, "onCreate: " + dateUtils.getCurYear() + "==" + dateUtils.getCurMonth() + "===" + dateUtils.getCurDay());
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PhoneUtils phoneUtils = new PhoneUtils(this);
//        Log.e(TAG, "onCreate: " + phoneUtils.getNativePhoneNumber());
//    }

    public void test(View view) {
//        Log.e(TAG, "test: ");
//        String a = "111111123456666";
//        StreamUtils.print(a.getBytes());
//        StreamUtils.string2File("123456778", Environment.getExternalStorageDirectory().getPath() + File.separator + "a.txt");
//        String s = StreamUtils.file2String(Environment.getExternalStorageDirectory().getPath() + File.separator + "a.txt");
//        Log.e(TAG, "test: " + s);
//        FrequencyAnalysis.printCharCount(Environment.getExternalStorageDirectory().getPath() + File.separator + "a.txt");
//        FrequencyAnalysis.encryptFile(Environment.getExternalStorageDirectory().getPath() + File.separator + "a.txt",
//                Environment.getExternalStorageDirectory().getPath() + File.separator + "b.txt",
//                1);
        int key = 3;
        String artile = StreamUtils.file2String(Environment.getExternalStorageDirectory().getPath() + File.separator + "a.txt");
        Log.e(TAG, "test: " + artile);
        FrequencyAnalysis.decryptCaesarCode(artile
                , Environment.getExternalStorageDirectory().getPath() + File.separator + "d.txt");
    }
}
