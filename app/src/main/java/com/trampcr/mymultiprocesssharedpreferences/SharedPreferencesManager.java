package com.trampcr.mymultiprocesssharedpreferences;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SharedPreferencesManager {
    public static final String PROCESS_1 = "com.trampcr.mymultiprocesssharedpreferences:process1";
    public static final String SP_NAME = "trampcr_sp_name";

    private volatile static SharedPreferencesManager mSharedPreferencesManager;
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    private SharedPreferencesManager(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (mSharedPreferencesManager == null) {
            synchronized (SharedPreferencesManager.class) {
                if (mSharedPreferencesManager == null) {
                    mSharedPreferencesManager = new SharedPreferencesManager(context);
                }
            }
        }

        return mSharedPreferencesManager;
    }

    public void setString(String key, String value) {
        if (PROCESS_1.equals(getProcessName())) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        } else {
            MyContentProvider.setStringValue(mContext, key, value);
        }
    }

    public String getString(String key, String defValue) {
        if (PROCESS_1.equals(getProcessName())) {
            return mSharedPreferences.getString(key, defValue);
        } else {
            return MyContentProvider.getStringValue(mContext, key, defValue);
        }
    }

    public String getProcessName() {
        int pid = android.os.Process.myPid();
        String processName = null;

        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return null;
        }

        List<ActivityManager.RunningAppProcessInfo> runningApps = activityManager.getRunningAppProcesses();
        if (runningApps == null || runningApps.size() == 0) {
            return null;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : runningApps) {
            if (appProcess.pid == pid) {
                processName = appProcess.processName;
            }
        }

        return processName;
    }
}
