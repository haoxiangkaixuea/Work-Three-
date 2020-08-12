package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.data.local.DBHelper;
import cn.edu.scujcc.workthreeweek.util.DataUtils;

/**
 * @author Administrator
 */
public class SQLiteActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button btnCreateDatabase;
    private Button btnAddDatabase;
    private Button btnUpdateDatabase;
    private Button btnQueryDatabase;
    private Button btnDeleteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite);

        btnCreateDatabase = findViewById(R.id.create_sqlite_database);
        btnCreateDatabase.setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.creare_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnAddDatabase = findViewById(R.id.add_sqlite_data);
        btnAddDatabase.setOnClickListener(v -> {
            DataUtils dataUtils = new DataUtils();
            dataUtils.addData();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnUpdateDatabase = findViewById(R.id.update_sqlite_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            DataUtils dataUtils = new DataUtils();
            dataUtils.updatetData();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.update_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnDeleteDatabase = findViewById(R.id.delete_sqlite_database);
        btnDeleteDatabase.setOnClickListener(v -> {
            DataUtils dataUtils = new DataUtils();
            dataUtils.deteleData();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnQueryDatabase = findViewById(R.id.qurey_sqlite_data);
        btnQueryDatabase.setOnClickListener(v -> {
            DataUtils dataUtils = new DataUtils();
            dataUtils.qureyData();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.query_database),
                    Toast.LENGTH_SHORT).show();
        });
    }
}