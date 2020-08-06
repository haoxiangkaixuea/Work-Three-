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
    public static final String ACTION = "use IntentService start ProgressBar";
    public static final int PROGRRESBAR_MAX = 100;
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceive localBroadcastReceive;
    private Button btStart;
    private ProgressBar pb;
    private TextView tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        btStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        initBroadcast();
        btStart.setOnClickListener(v -> {
            Intent intentService = new Intent(this, MyIntentService.class);
            startService(intentService);
        });
    }

    private void initBroadcast() {
        if (localBroadcastManager == null) {
            localBroadcastManager = LocalBroadcastManager.getInstance(this);
        }
        if (localBroadcastReceive == null) {
            localBroadcastReceive = new LocalBroadcastReceive();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        localBroadcastManager.registerReceiver(localBroadcastReceive, intentFilter);
    }


    public class LocalBroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION.equals(intent.getAction())) {
                int progress = intent.getIntExtra("progress", 0);
                if (progress > 0 && progress < PROGRRESBAR_MAX) {
                    pb.setVisibility(View.VISIBLE);
                    tvState.setText(getResources().getString(R.string.progress_star));
                } else if (progress >= PROGRRESBAR_MAX) {
                    pb.setVisibility(View.GONE);
                    tvState.setText(getResources().getString(R.string.progress_end));
                }
                pb.setProgress(progress);
            }
        }
    }
}