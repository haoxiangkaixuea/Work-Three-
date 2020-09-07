package cn.edu.scujcc.workthreeweek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.HandlerActivity;
import cn.edu.scujcc.workthreeweek.IntentServiceActivity;
import cn.edu.scujcc.workthreeweek.R;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSqLite = findViewById(R.id.start_s_q_lite);
        Button btnLitePal = findViewById(R.id.start_lite_pal);
        Button btnHandler = findViewById(R.id.start_handler);
        Button btnAsync = findViewById(R.id.start_async);
        Button btnIntentService = findViewById(R.id.start_intent_service);
        Button btnDataContent = findViewById(R.id.start_data_content);
        Button btnDatumContent = findViewById(R.id.start_datum_content);

        btnSqLite.setOnClickListener(this);
        btnLitePal.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnIntentService.setOnClickListener(this);
        btnDataContent.setOnClickListener(this);
        btnDatumContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_lite_pal:
                Intent litePalIntent = new Intent(MainActivity.this, LiteActivity.class);
                startActivity(litePalIntent);
                break;
            case R.id.start_s_q_lite:
                Intent sqLiteIntent = new Intent(MainActivity.this, SQLiteActivity.class);
                startActivity(sqLiteIntent);
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
            case R.id.start_datum_content:
                Intent datumContentIntent = new Intent(MainActivity.this, DatumContentActivity.class);
                startActivity(datumContentIntent);
                break;
            default:
                break;
        }
    }
}