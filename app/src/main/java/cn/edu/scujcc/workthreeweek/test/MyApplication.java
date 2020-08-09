package cn.edu.scujcc.workthreeweek.test;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyApplication extends Application {

    public static final String TAG = "MyApplication";

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "MyApplication attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyApplication onCreate");
    }
}
