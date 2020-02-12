UnityAndroidKiosk
==============================

By default this app starts and calls `startLockTask()` which sets it in screen pinning mode.

I *believe* we should be able to lock it down more with this;
`adb shell dpm set-device-owner com.android.kiosktest/.AdminReceiver`
to undo this... you need to wipe the phone.

Make sure the app supports Device Admin ... stuff... in the AndroidManifest.xml
https://developer.android.com/guide/topics/admin/device-admin.html#developing
