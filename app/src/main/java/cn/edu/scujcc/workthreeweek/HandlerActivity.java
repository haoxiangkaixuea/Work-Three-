package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class HandlerActivity extends AppCompatActivity {
    public static final int PROGRRESBAR_STAR = 1;
    public static final int PROGRRESBAR_END = -1;
    public static final int PROGRRESBAR_MAX = 100;
    //核心线程数
    private static final int CORE_POOL_SIZE = 1;
    //最大线程数
    private static final int MAXIMUM_POOL_SIZE = 3;
    //非核心线程闲时存活期
    private static final long KEEP_ALIVE = 10;
    public boolean isRunning = true;
    int data = 0;
    private BlockingQueue<Runnable> sPoolWorkQueue;
    private RejectedExecutionHandler sThreadFactory;
    // 1.创建线程池,通过配置核心参数，从而实现自定义线程池
    Executor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvState;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handlerMessage(Message msg) {
            switch (msg.what) {
                case PROGRRESBAR_STAR:
                    tvState.setText(getResources().getString(R.string.progress_star));
                    setStop(true);
                    pb.setProgress(data);
                    pb.setVisibility(View.VISIBLE);
                    break;
                case PROGRRESBAR_END:
                    tvState.setText(getResources().getString(R.string.progress_end));
                    setStop(false);
                    pb.setProgress(data);
                    pb.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };
    // 2.向线程池提交任务：execute()，传入Runnable对象
//    threadPool.execute(new
//
//    Runnable() {
//        @Override
//        public void run () {
//            int status = 0;
//            Message message = new Message();
//            if (status < PROGRRESBAR_MAX) {
//                status = startProgressBar();
//                message.what = PROGRRESBAR_STAR;
//                handler.sendMessage(message);
//            } else if (status == PROGRRESBAR_MAX) {
//                message.what = PROGRRESBAR_END;
//                handler.sendMessage(message);
//            }
//        }
//    });
//    // 3. 关闭线程池shutdown()
//            threadPool.shutdown();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btnStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        btnStart.setOnClickListener(v -> {
//            new Thread() {
//                int status;
//                Message message = new Message();
//
//                @Override
//                public void run() {
//                    if (status < PROGRRESBAR_MAX) {
//                        status = startProgressBar();
//                        message.what = PROGRRESBAR_STAR;
//                        handler.sendMessage(message);
//                    } else if (status == PROGRRESBAR_MAX) {
//                        message.what = PROGRRESBAR_END;
//                        handler.sendMessage(message);
//                    }
//                }
//            }.start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private int startProgressBar() {
        int date = 0;
        while (isRunning) {
            try {
                date += 1;
                if (date >= 100) {
                    isRunning = false;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setStop(boolean stop) {
        this.isRunning = stop;
    }
}
