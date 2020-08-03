package cn.edu.scujcc.workthreeweek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataabaseHelper extends SQLiteOpenHelper {
    //把数据库创建定义为一个字符串常量
    //创建图书数据库
    public static final String CREATE_BOOK = "create table if not exists Book("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    //创建图书类别数据库
    public static final String CREATE_CATEGROY = "create table if not exists Categroy("
            + "id integer primary key autoincrement,"
            + "categroy_name text,"
            + "categroy_code integer)";

    private Context mContext;

    public MyDataabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGROY);
        Toast.makeText(mContext, "Create SQLite Book", Toast.LENGTH_SHORT).show();
    }

    //更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
