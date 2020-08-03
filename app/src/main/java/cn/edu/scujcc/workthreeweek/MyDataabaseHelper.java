package cn.edu.scujcc.workthreeweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDataabaseHelper extends SQLiteOpenHelper {
    //把数据库创建定义为一个字符串常量
    public static final String CREATE_BOOK = "create table book("
            + "id integer primary key autoincrement,"
            + "author varchar(50),"
            + "price varchar(200),"
            + "pages varchar(10),"
            + "name varchar(10))";
    private Context mContext;

    public MyDataabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "Create SQLite Book", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
