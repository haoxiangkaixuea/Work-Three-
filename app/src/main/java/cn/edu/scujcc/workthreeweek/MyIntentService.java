package cn.edu.scujcc.workthreeweek;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * <pre>
 *     author : ylj
 *     e-mail : xxx@xx
 *     time   : 2020/08/06
 *     desc   : IntentService启动ProgressBar
 *     version: 1.0
 * </pre>
 */
public class MyIntentService extends IntentService {
    private int data = 0;
    private LocalBroadcastManager mLocalBroadcastManager;

    public MyIntentService() {
        super("MyIntentService");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (data <= 100) {
            try {
                data += 1;
                Thread.sleep(1000);
                sendThreadStatus(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendThreadStatus(int progress) {
        Intent intent = new Intent(IntentServiceActivity.ACTION);
        intent.putExtra("progress", progress);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 复写onStartCommand()方法
     * 默认实现 = 将请求的Intent添加到工作队列里
     **/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
