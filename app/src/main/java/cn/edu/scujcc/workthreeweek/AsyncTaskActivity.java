package cn.edu.scujcc.workthreeweek;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class AsyncTaskActivity extends AppCompatActivity {
    public static final int PROGRRESBAR_MAX = 100;
    private Button btnStart;
    private ProgressBar pb;
    private TextView tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btnStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        final ProgressBarTask progressBarTask = new ProgressBarTask(pb, tvState);
        btnStart.setOnClickListener(v -> {
            progressBarTask.execute();
        });
    }

    public class ProgressBarTask extends AsyncTask<Void, Integer, Boolean> {
        @SuppressLint("StaticFieldLeak")
        private TextView textView;
        @SuppressLint("StaticFieldLeak")
        private ProgressBar progressBar;

        public ProgressBarTask(ProgressBar progressBar, TextView textView) {
            this.progressBar = progressBar;
            this.textView = textView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int count = 0;
            try {
                while (count <= PROGRRESBAR_MAX) {
                    count += 1;
                    Thread.sleep(100);
                    publishProgress(count);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            textView.setText(getResources().getString(R.string.progress_star));
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.setVisibility(View.GONE);
            textView.setText(getResources().getString(R.string.progress_end));
            super.onPostExecute(result);
        }
    }
}