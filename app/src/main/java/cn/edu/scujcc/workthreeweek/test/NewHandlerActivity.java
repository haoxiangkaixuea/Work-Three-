package cn.edu.scujcc.workthreeweek.test;

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

import cn.edu.scujcc.workthreeweek.R;

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
    public boolean isRunning = true;
    int data = 0;
    private Button btnStart;
    private ProgressBar pb;
    public Runnable updateThread = new Runnable() {
        int i = 0;

        @Override
        public void run() {
            i += 10;
            Message msg = updateBarHandler.obtainMessage();
            msg.arg1 = i;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //将msg加入到消息队列中
            updateBarHandler.sendMessage(msg);
            if (i == 100) {
                tvState.setText(getResources().getString(R.string.progress_end));
                updateBarHandler.removeCallbacks(updateThread);
                pb.setVisibility(View.GONE);
            }
        }
    };
    private TextView tvState;
    //消息异步机制
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
        btnStart.setOnClickListener(new ButtonListener());
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            pb.setVisibility(View.VISIBLE);
            updateBarHandler.post(updateThread);
            tvState.setText(getResources().getString(R.string.progress_star));
        }
    }
}
