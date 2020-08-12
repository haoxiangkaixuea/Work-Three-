package cn.edu.scujcc.workthreeweek;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/09
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NewHandlerActivity extends AppCompatActivity {
    private static final int PROGRESSBAR_MAX = 100;
    public boolean isRunning = true;
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvState;
    private Runnable updateThread = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            i += 10;
            Message msg = updateBarHandler.obtainMessage();
            //可以避免重复创建Message对象，所以建议用updateBarHandler.obtainMessage()创建Message对象。
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将msg加入到消息队列中
            updateBarHandler.sendMessage(msg);
            if (i == PROGRESSBAR_MAX) {
                tvState.setText(getResources().getString(R.string.progress_end));
                updateBarHandler.removeCallbacks(updateThread);
                pb.setVisibility(View.GONE);
            }
        }
    };
    /**
     * 消息异步机制
     */
    public Handler updateBarHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            pb.setProgress(msg.arg1);
            updateBarHandler.post(updateThread);
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
            pb.setVisibility(View.VISIBLE);
            updateBarHandler.post(updateThread);
            tvState.setText(getResources().getString(R.string.progress_star));
        });
    }
}
