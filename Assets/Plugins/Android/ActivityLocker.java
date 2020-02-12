//	some package name to resolve ambiguity (same as github repos name)
package com.NewChromantics.UnityAndroidKiosk;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class ActivityLocker {
    private static Context context;

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static void lock() {
        Log.d("ActivityLocker", "lock()");

        Activity activity = (Activity) context;
        activity.startLockTask();
    }

    public static void unlock() {
        Log.d("ActivityLocker", "unlock()");

        Activity activity = (Activity) context;
        activity.stopLockTask();
    }
}