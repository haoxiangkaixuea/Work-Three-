package cn.edu.scujcc.workthreeweek.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
public class NewHandlerActivity extends AppCompatActivity {
    public static final int PROGRRESBAR_STAR = 1;
    public static final int PROGRRESBAR_END = -1;
    public static final int PROGRRESBAR_MAX = 100;
    private static final int MSG = 1;
    private static boolean isRunning;
    int data = 0;
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvState;
    private int p = 0;//当前进度
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

    private static void startProgressBar() {
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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btnStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        pb.setVisibility(View.VISIBLE);

    }

    public void setStop(boolean stop) {
        isRunning = stop;
    }


}
