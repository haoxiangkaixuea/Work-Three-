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

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class HandlerActivity extends AppCompatActivity {
    public static final int PROGRRESBAR_STAR = 1;
    public static final int PROGRRESBAR_END = -1;
    public int data = 0;
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvState;
    private Handler updateBarHandler = new Handler(Looper.getMainLooper()) {
        public void handlerMessage(Message msg) {
            switch (msg.what) {
                case PROGRRESBAR_STAR:
                    //tvState.setText(getResources().getString(R.string.progress_star));
                    //pb.setVisibility(View.VISIBLE);
                    pb.setProgress(msg.arg1);
                    break;
                case PROGRRESBAR_END:
                    //tvState.setText(getResources().getString(R.string.progress_end));
                    //pb.setVisibility(View.GONE);
                    pb.setProgress(msg.arg1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btnStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        btnStart.setOnClickListener(v -> {
            tvState.setText(getResources().getString(R.string.progress_star));
            pb.setVisibility(View.VISIBLE);
            //updateBarHandler.post(updateThread);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateBarHandler.removeCallbacksAndMessages(null);
    }

//    public void doWork() {
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
//        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
//        singleThreadPool.shutdown();
//
//        class TimerTaskThread extends Thread {
//            int i = 0;
//
//            public TimerTaskThread() {
//                i += 10;
//                Message msg = updateBarHandler.obtainMessage();
//                //可以避免重复创建Message对象，所以建议用updateBarHandler.obtainMessage()创建Message对象。
//                msg.arg1 = i;
//                msg.what = PROGRRESBAR_STAR;
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //将msg加入到消息队列中
//                updateBarHandler.sendMessage(msg);
//                if (i == 100) {
//                    msg.what = PROGRRESBAR_END;
//                    onDestroy();
//                }
//            }
//        }
//    }

    public class ExecutorsDemo {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        void main(String[] args) {

            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                pool.execute(new SubThread());
            }
        }
    }

    class SubThread implements Runnable {
        int i = 0;

        @Override
        public void run() {
            i += 10;
            Message msg = updateBarHandler.obtainMessage();
            //可以避免重复创建Message对象，所以建议用updateBarHandler.obtainMessage()创建Message对象。
            msg.arg1 = i;
            msg.what = PROGRRESBAR_STAR;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将msg加入到消息队列中
            updateBarHandler.sendMessage(msg);
            if (i == 100) {
                msg.what = PROGRRESBAR_END;
                tvState.setText(getResources().getString(R.string.progress_end));
                pb.setVisibility(View.GONE);
                onDestroy();
            }
        }
    }
}
