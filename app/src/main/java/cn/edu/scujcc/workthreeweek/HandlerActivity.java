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

/**
 * @author Administrator
 */
public class HandlerActivity extends AppCompatActivity {
    public static final int PROGRRESBAR_STAR = 1;
    public static final int PROGRRESBAR_END = -1;
    public static final int PROGRRESBAR_MAX = 100;
    int data = 0;
    private Button btStart;
    private ProgressBar pb;
    private TextView tvState;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handlerMessage(Message msg) {
            switch (msg.what) {
                case PROGRRESBAR_STAR:
                    tvState.setText(getResources().getString(R.string.progress_star));
                    pb.setProgress(data);
                    pb.setVisibility(View.VISIBLE);
                    break;
                case PROGRRESBAR_END:
                    tvState.setText(getResources().getString(R.string.progress_end));
                    pb.setProgress(data);
                    pb.setVisibility(View.GONE);
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

        btStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        btStart.setOnClickListener(v -> {
            pb.setVisibility(View.VISIBLE);
            tvState.setText(getResources().getString(R.string.progress_star));
        });
        new Thread() {
            int status;
            Message message = new Message();

            @Override
            public void run() {
                if (status < 100) {
                    status = startProgressBar();
                    message.what = PROGRRESBAR_STAR;
                    handler.sendMessage(message);
                } else if (status == PROGRRESBAR_MAX) {
                    message.what = PROGRRESBAR_END;
                    handler.sendMessage(message);
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private int startProgressBar() {
        while (data <= PROGRRESBAR_MAX) {
            data += 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
