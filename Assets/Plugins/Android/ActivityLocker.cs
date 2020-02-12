using UnityEngine;

public class ActivityLocker : MonoBehaviour
{
	private AndroidJavaObject playerActivityContext = null;
	static string JavaPluginClass = "com.NewChromantics.UnityAndroidKiosk.ActivityLocker";

	public void SaveContext()
	{
#if UNITY_ANDROID
		// First, obtain the current activity context
		using (var actClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
		{
			playerActivityContext = actClass.GetStatic<AndroidJavaObject>("currentActivity");
		}

		var plugin = new AndroidJavaClass(JavaPluginClass);
		plugin.CallStatic("setContext", playerActivityContext);
#endif
	}


	public void DoStartLockTask()
	{
		//com.unity3d.player.UnityPlayerActivity
		var unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
		var unityActivity = unityPlayerClass.GetStatic<AndroidJavaObject>("currentActivity");

		//	gr: this fails, when we do the same thing (call startLockTask on the currentActivity object, after being cast to Activity) in java, it's okay...
		unityActivity.Call("startLockTask", null);
	}


	void OnEnable()
	{
		LockButtonClicked();
	}


	public void LockButtonClicked()
	{
#if UNITY_ANDROID
		SaveContext();

		var plugin = new AndroidJavaClass(JavaPluginClass);
		plugin.CallStatic("lock");
#endif
	}

	public void UnlockButtonClicked()
	{
#if UNITY_ANDROID
		SaveContext();

		var plugin = new AndroidJavaClass(JavaPluginClass);
		plugin.CallStatic("unlock");
#endif
	}
}
