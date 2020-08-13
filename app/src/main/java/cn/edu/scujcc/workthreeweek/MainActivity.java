package cn.edu.scujcc.workthreeweek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSQLite;
    private Button btnLitePal;
    private Button btnHandler;
    private Button btnAsync;
    private Button btnIntentService;
    private Button btnDataContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSQLite = findViewById(R.id.start_s_q_lite);
        btnLitePal = findViewById(R.id.start_lite_pal);
        btnHandler = findViewById(R.id.start_handler);
        btnAsync = findViewById(R.id.start_async);
        btnIntentService = findViewById(R.id.start_intent_service);
        btnDataContent = findViewById(R.id.start_data_content);

        btnSQLite.setOnClickListener(this);
        btnLitePal.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnIntentService.setOnClickListener(this);
        btnDataContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_s_q_lite:
                Intent sqLiteIntent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(sqLiteIntent);
                break;
            case R.id.start_lite_pal:
                Intent litePalIntent = new Intent(MainActivity.this, LiteActivity.class);
                startActivity(litePalIntent);
                break;
            case R.id.start_handler:
                Intent handlerIntent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(handlerIntent);
                break;
            case R.id.start_async:
                Intent asyncIntent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(asyncIntent);
                break;
            case R.id.start_intent_service:
                Intent intentService = new Intent(MainActivity.this, IntentServiceActivity.class);
                startActivity(intentService);
                break;
            case R.id.start_data_content:
                Intent dataContentIntent = new Intent(MainActivity.this, DataContentActivity.class);
                startActivity(dataContentIntent);
                break;
            default:
                break;
        }
    }
}