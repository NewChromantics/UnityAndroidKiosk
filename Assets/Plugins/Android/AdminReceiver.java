package com.android.kiosktest;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.content.ComponentName;

public class AdminReceiver extends android.app.admin.DeviceAdminReceiver
{
  void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
		Log.d("ActivityLocker", "onEnabled()");
        showToast(context, "AdminReceiver on enabled");
   		super.onEnabled(context, intent);
 
    }

  public static ComponentName getComponentName(Context context) {
	  return new ComponentName(context.getApplicationContext(), AdminReceiver.class);
  }

}
