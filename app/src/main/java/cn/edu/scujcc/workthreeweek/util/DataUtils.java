package cn.edu.scujcc.workthreeweek.util;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.edu.scujcc.workthreeweek.data.local.DBHelper;

/**
 * @author Administrator
 */
public class DataUtils {
    private DBHelper myDbHelper;

    public void addData() {
        SQLiteDatabase database = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //myDbHelper = new MyDatabaseHelper(this, "BookStore", null, 1);
        //更新了数据库，版本version改为了2
        //装入数据
        values.put("name", "悲惨世界");
        values.put("author", "雨果");
        values.put("price", "23");
        values.put("pages", "123456");
        database.insert("Book", null, values);
    }

    public void addSecondData() {
        SQLiteDatabase database = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "红楼梦");
        values.put("author", "曹雪芹");
        values.put("price", "44");
        values.put("pages", "33446");
        //插入第二条数据
        database.insert("Book", null, values);
    }

    public void updateData() {
        SQLiteDatabase database = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", "22");
        //?表示一个占位符，表示更新所有name=？的行，通过第四个参数提供的字符串作为指定的相应的内容
        //想要在Book数据库中，想要把悲惨世界这本书的价格改为22
        database.update("Book", values, "name=?", new String[]{"悲惨世界"});
    }

    public void deleteData() {
        SQLiteDatabase database = myDbHelper.getWritableDatabase();
        //要删除在Book数据库中，pages大于33446的书
        database.delete("Book", "pages > ?", new String[]{"33446"});
    }

    public void queryData() {
        SQLiteDatabase database = myDbHelper.getWritableDatabase();
        Cursor cursor = database.query("Book", null, null, null,
                null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
            } while (cursor.moveToFirst());
        }
    }
}
