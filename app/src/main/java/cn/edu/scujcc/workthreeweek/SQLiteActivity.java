package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.data.local.DBHelper;

import static org.litepal.LitePalApplication.getContext;

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
        dbHelper = new DBHelper(getContext());
        btnCreateDatabase.setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.create_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnAddDatabase = findViewById(R.id.add_sqlite_data);
        btnAddDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "悲惨世界");
            values.put("author", "雨果");
            values.put("price", "23");
            values.put("pages", "123456");
            database.insert("Book", null, values);
            values.clear();
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnUpdateDatabase = findViewById(R.id.update_sqlite_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", "22");
            database.update("Book", values, "name=?", new String[]{"悲惨世界"});
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.update_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnDeleteDatabase = findViewById(R.id.delete_sqlite_database);
        btnDeleteDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            //要删除在Book数据库中，pages大于33446的书
            database.delete("Book", "pages > ?", new String[]{"33446"});
            Toast.makeText(SQLiteActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnQueryDatabase = findViewById(R.id.qurey_sqlite_data);
        btnQueryDatabase.setOnClickListener(v -> {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            Cursor cursor = database.query("Book", null, null, null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    Log.d("name+author+price+pages", name + author + price + pages);
                    Toast.makeText(SQLiteActivity.this,
                            getResources().getString(R.string.query_database) + name + author + price + pages,
                            Toast.LENGTH_SHORT).show();
                } while (cursor.moveToNext());
            }
            cursor.close();
        });
    }
}