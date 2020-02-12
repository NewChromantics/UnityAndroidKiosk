//	some package name to resolve ambiguity (same as github repos name)
package com.android.kiosktest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.app.admin.SystemUpdatePolicy;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class ActivityLocker {
    private static Context context;

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static void lock() {
        Log.d("ActivityLocker", "lock()");

        Activity activity = (Activity) context;
        activity.startLockTask();
		
		setDefaultCosuPolicies(true);
    }

    public static void unlock() {
        Log.d("ActivityLocker", "unlock()");

        Activity activity = (Activity) context;
        activity.stopLockTask();
    }

	//	https://codelabs.developers.google.com/codelabs/cosu/index.html#7
	public static void setDefaultCosuPolicies(boolean active){

		Activity activity = (Activity) context;
			
		//	DeviceAdminReceiver from https://raw.githubusercontent.com/googlecodelabs/cosu/master/cosu_codelab_complete/app/src/main/java/com/google/codelabs/cosu/DeviceAdminReceiver.java
		ComponentName mAdminComponentName = AdminReceiver.getComponentName(activity);
		DevicePolicyManager mDevicePolicyManager = (DevicePolicyManager)
		activity.getSystemService(Context.DEVICE_POLICY_SERVICE);
		
		//	turn on & off features
		/*
		 LOCK_TASK_FEATURE_NONE,
		 LOCK_TASK_FEATURE_SYSTEM_INFO,
		 LOCK_TASK_FEATURE_NOTIFICATIONS,
		 LOCK_TASK_FEATURE_HOME,
		 LOCK_TASK_FEATURE_OVERVIEW,
		 LOCK_TASK_FEATURE_GLOBAL_ACTIONS,
		 and LOCK_TASK_FEATURE_KEYGUARD
		 */
		//	gr: this doesn't seem to be doing anything
		//	if these symbols are missing, you're probbaly using androidsdk < 28
		mDevicePolicyManager.setLockTaskFeatures(mAdminComponentName,DevicePolicyManager.LOCK_TASK_FEATURE_NONE);
		
		// Set user restrictions
		//	gr: docs say these no longer work
		//	https://developer.android.com/reference/android/os/UserManager
		/*
		setUserRestriction(UserManager.DISALLOW_SAFE_BOOT, active);
		setUserRestriction(UserManager.DISALLOW_FACTORY_RESET, active);
		setUserRestriction(UserManager.DISALLOW_ADD_USER, active);
		setUserRestriction(UserManager.DISALLOW_MOUNT_PHYSICAL_MEDIA, active);
		setUserRestriction(UserManager.DISALLOW_ADJUST_VOLUME, active);
		 */
		//	this DOES do something! (global to the phone)
		mDevicePolicyManager.addUserRestriction(mAdminComponentName, UserManager.DISALLOW_ADJUST_VOLUME);
		mDevicePolicyManager.addUserRestriction(mAdminComponentName, UserManager.DISALLOW_CREATE_WINDOWS);

		// Disable keyguard and status bar
		//mDevicePolicyManager.setKeyguardDisabled(mAdminComponentName, active);
		//mDevicePolicyManager.setStatusBarDisabled(mAdminComponentName, active);
/*
		// Enable STAY_ON_WHILE_PLUGGED_IN
		enableStayOnWhilePluggedIn(active);

		// Set system update policy
		if (active){
			mDevicePolicyManager.setSystemUpdatePolicy(mAdminComponentName,
					SystemUpdatePolicy.createWindowedInstallPolicy(60, 120));
		} else {
			mDevicePolicyManager.setSystemUpdatePolicy(mAdminComponentName,
					null);
		}

		// set this Activity as a lock task package
		mDevicePolicyManager.setLockTaskPackages(mAdminComponentName,
				active ? new String[]{getPackageName()} : new String[]{});

		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_MAIN);
		intentFilter.addCategory(Intent.CATEGORY_HOME);
		intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

		if (active) {
			// set Cosu activity as home intent receiver so that it is started
			// on reboot
			mDevicePolicyManager.addPersistentPreferredActivity(
					mAdminComponentName, intentFilter, new ComponentName(
						   getPackageName(), LockedActivity.class.getName()));
		} else {
			mDevicePolicyManager.clearPackagePersistentPreferredActivities(
					mAdminComponentName, getPackageName());
		}
 */
	}
}
