package cn.edu.scujcc.workthreeweek.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * 表名
     */
    public static final String BOOK_TABLE_NAME = "book";
    public static final String CATEGORY_TABLE_NAME = "category";
    /**
     * 把数据库创建定义为一个字符串常量
     * 创建图书数据库
     */
    public static final String CREATE_BOOK = "create table if not exists Book("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    /**
     * 创建图书类别数据库
     * CREATE_CATEGORY 图书类别数据库
     */
    public static final String CREATE_CATEGORY = "create table if not exists Category("
            + "id integer primary key autoincrement,"
            + "categroy_name text,"
            + "categroy_code integer)";
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "demo.db";
    /**
     * 数据库版本号
     */
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

    /**
     * 更新数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String createBook = "DROP TABLE IF EXISTS CREATE_BOOK";
        String createCategory = "DROP TABLE IF EXISTS CREATE_CATEGORY";
        sqLiteDatabase.execSQL(createBook);
        sqLiteDatabase.execSQL(createCategory);
        onCreate(sqLiteDatabase);
    }
}
