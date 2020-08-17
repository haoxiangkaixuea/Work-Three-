package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class DataContentActivity extends AppCompatActivity implements View.OnClickListener {
    private String newId;
    private Button btnDelete;
    private Button btnInsert;
    private Button btnQuery;
    private Button btnUpdate;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_content);

        btnInsert = findViewById(R.id.add_content_data);
        btnQuery = findViewById(R.id.query_content_data);
        btnUpdate = findViewById(R.id.update_content_database);
        btnDelete = findViewById(R.id.delete_content_database);

        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_content_database:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book" + newId);
                getContentResolver().delete(uri, null, null);
                Toast.makeText(DataContentActivity.this,
                        getResources().getString(R.string.delete_database),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_content_data:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
                ContentValues values = new ContentValues();
                values.put("name", "悲惨世界");
                values.put("author", "雨果");
                values.put("price", "23");
                values.put("pages", "123456");
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                Toast.makeText(DataContentActivity.this,
                        getResources().getString(R.string.add_data),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_content_data:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Toast.makeText(DataContentActivity.this,
                                author + name + pages + price,
                                Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                }
                break;
            case R.id.update_content_database:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book" + newId);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "红楼梦");
                contentValues.put("author", "曹雪芹");
                getContentResolver().update(uri, contentValues, null, null);
                Toast.makeText(DataContentActivity.this,
                        getResources().getString(R.string.update_database),
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
