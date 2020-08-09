package cn.edu.scujcc.workthreeweek;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView5;

    private Uri bookUser;
    private Uri categoryUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        initView();
    }

    /**
     * 初始化各种View
     */

    private void initView() {
        TextView textView1 = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView22 = findViewById(R.id.textView22);
        TextView textView33 = findViewById(R.id.textView33);
        TextView textView44 = findViewById(R.id.textView44);
        textView5 = findViewById(R.id.textView5);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView11.setOnClickListener(this);
        textView22.setOnClickListener(this);
        textView33.setOnClickListener(this);
        textView44.setOnClickListener(this);

        // 设置user表URI
        bookUser = Uri.parse("content://cn.edu.scujcc.workthreeweekr/book");
        // 设置job表URI
        categoryUser = Uri.parse("content://cn.edu.scujcc.workthreeweek/category");
    }

    /**
     * 各种点击事件的方法
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:
                //增 book表
                insert(1);
                break;
            case R.id.textView2:
                //删 book表
                delete(1);
                break;
            case R.id.textView3:
                //改 book表
                update(1);
                break;
            case R.id.textView4:
                //查 book表
                query(1);
                break;
            case R.id.textView11:
                //增 category表
                insert(2);
                break;
            case R.id.textView22:
                //删 category表
                delete(2);
                break;
            case R.id.textView33:
                //改 category表
                update(2);
                break;
            case R.id.textView44:
                //查 category表
                query(2);
                break;
            default:
                break;
        }
    }

    /**
     * 插入数据
     */

    private void insert(int mode) {
        String name;
        String author;
        double price;
        int pages;
        Uri uri;
        if (mode == 1) {
            name = "红楼梦 book表";
            author = "曹雪芹 book表";
            price = 124.1;
            pages = 234;
            uri = bookUser;
            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("name", name);
            values.put("author", author);
            values.put("price", price);
            values.put("pages", pages);
            // 获取ContentResolver
            ContentResolver resolver = getContentResolver();
            // 通过ContentResolver 向ContentProvider中插入数据
            resolver.insert(uri, values);
        } else {
            String categoryName;
            String categoryCode;
            categoryName = "文学类 category表";
            categoryCode = "001，我是詹姆斯 category表";
            uri = categoryUser;
            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("categoryName", categoryName);
            values.put("categoryCode", categoryCode);
            // 获取ContentResolver
            ContentResolver resolver = getContentResolver();
            // 通过ContentResolver 向ContentProvider中插入数据
            resolver.insert(uri, values);
        }
    }

    /**
     * 删除数据
     */

    private void delete(int mode) {
        Uri uri;
        if (mode == 1) {
            uri = bookUser;
        } else {
            uri = categoryUser;
        }
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        // 通过ContentResolver 将ContentProvider中某条数据删除
        resolver.delete(uri, "id=1", null);
    }

    /**
     * 修改数据
     */

    private void update(int mode) {
        String name;
        String author;
        double price;
        int pages;
        Uri uri;
        if (mode == 1) {
            name = "悲惨世界 user表";
            author = "雨果 user表";
            price = 124.1;
            pages = 234;
            uri = bookUser;
            ContentResolver resolver = getContentResolver();
            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("name", name);
            values.put("author", author);
            values.put("price", price);
            values.put("pages", pages);
            resolver.update(uri, values, "id=1", null);
        } else {
            String categoryName;
            String categoryCode;
            categoryName = "外国文学类 category表";
            categoryCode = "002，我修改成功了 category表";
            uri = categoryUser;
            // 获取ContentResolver
            ContentResolver resolver = getContentResolver();
            ContentValues values = new ContentValues();
            values.put("id", 1);
            values.put("categoryName", categoryName);
            values.put("categoryCode", categoryCode);
            // 获取ContentResolver
            // 通过ContentResolver 向ContentProvider中插入数据
            resolver.update(uri, values, "id=1", null);
        }
    }

    /**
     * 查数据
     */

    private void query(int mode) {
        Uri uri;
        if (mode == 1) {
            uri = bookUser;
        } else {
            uri = categoryUser;
        }

        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri, new String[]{"id", "name", "author", "price", "pages", "categoryName", "categoryCode"}, null, null, null);
        StringBuilder sbBuilder = new StringBuilder();
        assert cursor != null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            sbBuilder.append("ID:").append(id).append("\n");
            String name = cursor.getString(cursor.getColumnIndex("name"));
            sbBuilder.append("姓名:").append(name).append("\n");
            String author = cursor.getString(cursor.getColumnIndex("author"));
            sbBuilder.append("作者:").append(author).append("\n\n\n");
            String price = cursor.getString(cursor.getColumnIndex("price"));
            sbBuilder.append("价格:").append(price).append("\n\n\n");
            String pages = cursor.getString(cursor.getColumnIndex("pages"));
            sbBuilder.append("页数:").append(pages).append("\n\n\n");
            String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
            sbBuilder.append("类型名字:").append(categoryName).append("\n\n\n");
            String categoryCode = cursor.getString(cursor.getColumnIndex("categoryCode"));
            sbBuilder.append("类型代码:").append(categoryCode).append("\n\n\n");
        }
        textView5.setText(sbBuilder.toString());
        cursor.close();
        // 关闭游标
    }

}