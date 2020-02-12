using UnityEngine;

public class StartLockTask : MonoBehaviour
{
	private AndroidJavaObject playerActivityContext = null;
	static string JavaPluginClass = "com.android.kiosktest.PluginClass";

	public void SaveContext()
	{
#if UNITY_ANDROID
		// First, obtain the current activity context
		using (var actClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer"))
		{
			playerActivityContext = actClass.GetStatic<AndroidJavaObject>("currentActivity");
		}

		var plugin = new AndroidJavaClass(JavaPluginClass);
		plugin.CallStatic<bool>("setContext", playerActivityContext);
#endif
	}


	public void DoStartLockTask()
	{
		//com.unity3d.player.UnityPlayerActivity
		var unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
		var unityActivity = unityPlayerClass.GetStatic<AndroidJavaObject>("currentActivity");

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
		bool retVal = plugin.CallStatic<bool>("lock", 7);
#endif
	}

	public void UnlockButtonClicked()
	{
#if UNITY_ANDROID
		SaveContext();

		var plugin = new AndroidJavaClass(JavaPluginClass);
		bool retVal = plugin.CallStatic<bool>("unlock", 7);
#endif
	}
}
