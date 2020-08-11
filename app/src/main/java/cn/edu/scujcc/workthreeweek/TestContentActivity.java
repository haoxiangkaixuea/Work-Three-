package cn.edu.scujcc.workthreeweek;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class TestContentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TestContentActivity";
    private String newId;
    private Button btnDelete;
    private Button btnInsert;
    private Button btnQuery;
    private Button btnUpdate;
    private ContentResolver resolver;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcontent);


        btnInsert = (Button) findViewById(R.id.add_content_data);
        btnQuery = (Button) findViewById(R.id.qurey_content_data);
        btnUpdate = (Button) findViewById(R.id.update_content_database);
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
                //textview.setText("已删除数据");
                Log.d(TAG, "已删除数据");
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
                Log.d(TAG, "已添加数据");
                //textview.setText("已添加数据");
                break;
            case R.id.qurey_content_data:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "查询结果为" + author + name + pages + price);
                        //textview.setText(author + name + pages + price);
                    }
                }
                cursor.close();
                break;
            case R.id.update_content_database:
                uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book" + newId);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "红楼梦");
                contentValues.put("author", "曹雪芹");
                getContentResolver().update(uri, contentValues, null, null);
                // textview.setText("已修改数据");
                Log.d(TAG, "已修改数据");
                break;
            default:
                break;
        }
    }
}
