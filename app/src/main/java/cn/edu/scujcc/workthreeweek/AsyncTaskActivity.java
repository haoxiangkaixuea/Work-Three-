package cn.edu.scujcc.workthreeweek;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AsyncTaskActivity extends AppCompatActivity {
    private Button btStart;
    private ProgressBar pb;
    private TextView tvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        btStart = findViewById(R.id.bt_start);
        pb = findViewById(R.id.pb);
        tvState = findViewById(R.id.tv_state);
        final ProgressBarTask progressBarTask = new ProgressBarTask(pb, tvState);
        btStart.setOnClickListener(v -> {
            progressBarTask.execute();
        });
    }

    public static class ProgressBarTask extends AsyncTask<Void, Integer, Boolean> {
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
            textView.setText("进程开始");
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int count = 0;
            try {
                while (count <= 100) {
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
            progressBar.setVisibility(View.GONE);
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            textView.setText("进程结束");
            super.onPostExecute(result);
        }
    }
}