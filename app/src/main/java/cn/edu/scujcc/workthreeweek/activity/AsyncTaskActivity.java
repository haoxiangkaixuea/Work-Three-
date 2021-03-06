package cn.edu.scujcc.workthreeweek.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.R;

/**
 * @author Administrator
 */
public class AsyncTaskActivity extends AppCompatActivity {
    public static final int PROGRESSBAR_MAX = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        Button btnStart = findViewById(R.id.bt_start);
        ProgressBar pb = findViewById(R.id.pb);
        TextView tvStatus = findViewById(R.id.tv_state);
        ProgressBarTask progressBarTask = new ProgressBarTask(pb, tvStatus);
        btnStart.setOnClickListener(v -> progressBarTask.execute());
    }

    @SuppressLint("StaticFieldLeak")
    public class ProgressBarTask extends AsyncTask<Void, Integer, Boolean> {
        private TextView textView;
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
                while (count <= PROGRESSBAR_MAX) {
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