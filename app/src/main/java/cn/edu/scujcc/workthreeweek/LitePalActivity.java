package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import cn.edu.scujcc.workthreeweek.util.LitePalUtils;

public class LitePalActivity extends AppCompatActivity {
    private Button createDatabase;
    private Button addDatabase;
    private Button updateDatabase;
    private Button queryDatabase;
    private Button deteleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        createDatabase = findViewById(R.id.creare_litepal_database);
        createDatabase.setOnClickListener(v -> {
            LitePal.getDatabase();
            Toast.makeText(LitePalActivity.this, "创建数据库成功", Toast.LENGTH_SHORT).show();
        });

        addDatabase = findViewById(R.id.add_litepal_data);
        addDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.addData();
            Toast.makeText(LitePalActivity.this, "添加数据库成功", Toast.LENGTH_SHORT).show();
        });

        updateDatabase = findViewById(R.id.update_litepal_database);
        updateDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.updateData();
            Toast.makeText(LitePalActivity.this, "更新数据成功", Toast.LENGTH_SHORT).show();
        });

        deteleDatabase = findViewById(R.id.detele_litepal_database);
        deteleDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.deteleData();
            Toast.makeText(LitePalActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
        });

        queryDatabase = findViewById(R.id.qurey_litepal_data);
        queryDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.queryData();
            Toast.makeText(LitePalActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
        });
    }
}