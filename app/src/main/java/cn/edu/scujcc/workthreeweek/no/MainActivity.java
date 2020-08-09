package cn.edu.scujcc.workthreeweek.no;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.R;
import cn.edu.scujcc.workthreeweek.data.model.Student;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ContentProviderClient";
    public static final String URI_STRING = "content://cn.edu.scujcc.workthreeweek.StudentProvider";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SEX = "sex";
    public static final String METHOD = "contentProviderClientMethod";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity onCreate");
        Uri uri = Uri.parse(URI_STRING);
        ContentValues mContentValues = new ContentValues();
        mContentValues.put(ID, 34);
        mContentValues.put(NAME, "陈紫曦");
        mContentValues.put(SEX, "女");
        //插入数据到ContentProviderServer的数据库
        getContentResolver().insert(uri, mContentValues);
        //查询ContentProviderServer的数据库
        Cursor cursor = getContentResolver().query(uri, new String[]{NAME, SEX}, null, null, null);
        while (cursor.moveToNext()) {
            Student mGame = new Student(cursor.getString(0), cursor.getString(1));
            Log.d(TAG, "名字：" + mGame.mName + " 性别是：" + mGame.mSex);
        }
        //调用ContentProviderServer里面StudentProvider的call方法，可以简单得到ContentProviderServer中携带想要的数据的bundle过来
        Bundle bundle = getContentResolver().call(uri, METHOD, null, null);
        Log.d(TAG, "调用ContentProviderServer里面的方法得到的结果是：" + bundle.getString(METHOD));
    }
}
