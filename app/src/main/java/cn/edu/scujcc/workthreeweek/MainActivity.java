package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SQLiteDatabase database = mydbHelper.getWritableDatabase();
    private MyDataabaseHelper mydbHelper;
    private Button createDatabase, addDatabase, deteleDatabase, updateDatabase, queryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        //mydbHelper = new MyDataabaseHelper(this, "BookStore", null, 1);
        //更新了数据库，版本version改为了2
        mydbHelper = new MyDataabaseHelper(this, "BookStore", null, 2);
        createDatabase = findViewById(R.id.creare_database);
        createDatabase.setOnClickListener(v -> {
            mydbHelper.getWritableDatabase();
        });

        //添加数据
        addDatabase = findViewById(R.id.add_data);
        addDatabase.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            //装入数据
            values.put("name", "悲惨世界");
            values.put("author", "雨果");
            values.put("price", "23");
            values.put("pages", "123456");
            //插入数据
            database.insert("Book", null, values);
            //装入第二条数据
            values.put("name", "红楼梦");
            values.put("author", "曹雪芹");
            values.put("price", "44");
            values.put("pages", "33446");
            //插入第二条数据
            database.insert("Book", null, values);
        });

        //更新数据
        updateDatabase = findViewById(R.id.update_database);
        updateDatabase.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("price", "22");
            //?表示一个占位符，表示更新所有name=？的行，通过第四个参数提供的字符串作为指定的相应的内容
            //想要在Book数据库中，想要把悲惨世界这本书的价格改为22
            database.update("Book", values, "name=?", new String[]{"悲惨世界"});

        });

        //删除数据
        deteleDatabase = findViewById(R.id.detele_database);
        deteleDatabase.setOnClickListener(v -> {
            //要删除在Book数据库中，pages大于33446的书
            database.delete("Book", "pages > ?", new String[]{"33446"});

        });

        //查询数据
        queryDatabase = findViewById(R.id.qurey_data);
        queryDatabase.setOnClickListener(v -> {
            Cursor cursor = database.query("Book", null, null, null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    Log.d(TAG, "book name is" + name);
                    Log.d(TAG, "book author is" + author);
                    Log.d(TAG, "book price is" + price);
                    Log.d(TAG, "book pages is" + pages);
                } while (cursor.moveToFirst());
            }

        });
    }
}