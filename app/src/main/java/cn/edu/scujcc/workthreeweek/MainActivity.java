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

import cn.edu.scujcc.workthreeweek.data.local.MyDBHelper;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    //获取一个内容解析者
    private ContentResolver contentResolver;
    private Uri uri;
    private MyDBHelper mydbHelper;
    private Button btnCreateDatabase;
    private Button btnAddDatabase;
    private Button btnUpdateDatabase;
    private Button btnQueryDatabase;
    private Button btnDeteleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        mydbHelper = new MyDBHelper(this, "BookStore", null, 2);
        btnCreateDatabase = findViewById(R.id.creare_database);
        btnCreateDatabase.setOnClickListener(this);

        //添加数据
        btnAddDatabase = findViewById(R.id.add_data);
        btnAddDatabase.setOnClickListener(this
        );

        //更新数据
        btnUpdateDatabase = findViewById(R.id.updatel_database);
        btnUpdateDatabase.setOnClickListener(this
        );

        //删除数据
        btnDeteleDatabase = findViewById(R.id.detele_database);
        btnDeteleDatabase.setOnClickListener(this);

        //查询数据
        btnQueryDatabase = findViewById(R.id.qurey_data);
        btnQueryDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creare_database:
                mydbHelper.getWritableDatabase();
                break;
            case R.id.add_data:
                insert();
                break;
            case R.id.qurey_data:
                query();
                break;
            case R.id.updatel_database:
                update();
                break;
            case R.id.detele_database:
                delete();
                break;
            default:
        }
    }

    private void delete() {
        contentResolver.delete(uri, "name=?", new String[]{"曹雪芹"});
    }

    private void update() {
        //创建一个新的values
        ContentValues values = new ContentValues();
        values.put("name", "红楼梦");
        values.put("author", "曹雪芹");
        values.put("price", "44");
        values.put("pages", "33446");
        contentResolver.update(uri, values, "name=?", new String[]{"曹雪芹"});
    }

    private void query() {
        //projection 要查询的列名
        //selection 查询条件 name=?
        //selectionArgs 查询条件占位符的内容“张三”
        //sortOrder 排序
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            Log.e(TAG, "query: " + name + "," + author);
        }
    }

    private void insert() {
        //创建一个value
        ContentValues values = new ContentValues();
        values.put("name", "红楼梦");
        values.put("author", "曹雪芹");
        //根据内容提供者配置一个uri
        contentResolver.insert(uri, values);
    }
}