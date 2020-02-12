package com.android.kiosktest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class PluginClass {
    private static Context context;

    public static boolean setContext(Context ctx) {
        context = ctx;
        return true;
    }

    public static boolean lock(int number) {
        Log.d("SOME TAG", "onReceive Lock");

        Activity activity = (Activity) context;
        activity.startLockTask();

        return true;
    }

    public static boolean unlock(int number) {
        Log.d("SOME TAG", "onReceive Unlock");

        Activity activity = (Activity) context;
        activity.stopLockTask();

        return true;
    }
}