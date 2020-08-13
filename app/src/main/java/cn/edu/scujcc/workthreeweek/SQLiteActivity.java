package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.data.local.CategoryDBHelper;

/**
 * @author Administrator
 */
public class SQLiteActivity extends AppCompatActivity {
    private CategoryDBHelper categoryDBHelper;
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
        categoryDBHelper = new CategoryDBHelper(this, "Category.db", null, 1);
        btnCreateDatabase.setOnClickListener(v -> {
            categoryDBHelper.getWritableDatabase();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.create_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnAddDatabase = findViewById(R.id.add_sqlite_data);
        btnAddDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = categoryDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("category_name", "文学类");
            values.put("category_code", "001");
            database.insert("Category", null, values);
            values.clear();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnUpdateDatabase = findViewById(R.id.update_sqlite_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = categoryDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("category_code", "101");
            database.update("Category", values, "category_name=?", new String[]{"文学类"});
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.update_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnDeleteDatabase = findViewById(R.id.delete_sqlite_database);
        btnDeleteDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = categoryDBHelper.getWritableDatabase();
            //要删除在Book数据库中，pages大于33446的书
            database.delete("Category", "category_name = ?", new String[]{"文学类"});
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnQueryDatabase = findViewById(R.id.qurey_sqlite_data);
        btnQueryDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = categoryDBHelper.getWritableDatabase();
            Cursor cursor = database.query("Category", null, null, null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String category_name = cursor.getString(cursor.getColumnIndex("category_name"));
                    String category_code = cursor.getString(cursor.getColumnIndex("category_code"));
                    Log.d("name+code", category_name + category_code);
                    Toast.makeText(SQLiteActivity.this,
                            getResources().getString(R.string.query_database) + category_name + category_code,
                            Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
    }
}