package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import cn.edu.scujcc.workthreeweek.util.LitePalUtils;

/**
 * @author Administrator
 */
public class LitePalActivity extends AppCompatActivity {
    private Button btnCreateDatabase;
    private Button btnAddDatabase;
    private Button btnUpdateDatabase;
    private Button btnQueryDatabase;
    private Button btnDeleteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        btnCreateDatabase = findViewById(R.id.create_litepal_database);
        btnCreateDatabase.setOnClickListener(v -> {
            LitePal.getDatabase();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.create_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnAddDatabase = findViewById(R.id.add_litepal_data);
        btnAddDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.addData();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnUpdateDatabase = findViewById(R.id.update_litepal_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.updateData();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.update_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnDeleteDatabase = findViewById(R.id.delete_litepal_database);
        btnDeleteDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.deleteData();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnQueryDatabase = findViewById(R.id.qurey_litepal_data);
        btnQueryDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.queryData();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.query_database),
                    Toast.LENGTH_SHORT).show();
        });
    }
}