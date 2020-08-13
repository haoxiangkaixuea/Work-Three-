package cn.edu.scujcc.workthreeweek;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * @author Administrator
 */
public class IntentServiceActivity extends AppCompatActivity {
    public static final String ACTION = "cn.edu.scujcc.workthreeweek.IntentServiceActivity";
    public static final int PROGRESSBAR_MAX = 100;
    private LocalBroadcastManager localBroadcastManager;
    private MyLocalBroadcastReceive myLocalBroadcastReceive;
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        btnStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvStatus = findViewById(R.id.tv_state);
        initBroadcast();
        btnStart.setOnClickListener(v -> {
            pb.setVisibility(View.VISIBLE);
            Intent intentService = new Intent(IntentServiceActivity.this, MyIntentService.class);
            startService(intentService);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localBroadcastManager != null && myLocalBroadcastReceive != null) {
            localBroadcastManager.unregisterReceiver(myLocalBroadcastReceive);
        }
    }

    private void initBroadcast() {
        if (localBroadcastManager == null) {
            localBroadcastManager = LocalBroadcastManager.getInstance(this);
        }
        if (myLocalBroadcastReceive == null) {
            myLocalBroadcastReceive = new MyLocalBroadcastReceive();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        localBroadcastManager.registerReceiver(myLocalBroadcastReceive, intentFilter);
    }

    public class MyLocalBroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION.equals(intent.getAction())) {
                int progress = intent.getIntExtra("progress", 0);
                if (progress > 0 && progress < PROGRESSBAR_MAX) {
                    tvStatus.setText(getResources().getString(R.string.progress_star));
                } else if (progress >= PROGRESSBAR_MAX) {
                    tvStatus.setText(getResources().getString(R.string.progress_end));
                    pb.setVisibility(View.GONE);
                }
                pb.setProgress(progress);
            }
        }
    }
}