package cn.edu.scujcc.workthreeweek.no;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.R;
import cn.edu.scujcc.workthreeweek.data.local.DBHelper;
import cn.edu.scujcc.workthreeweek.util.ContentUtils;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener {
    private DBHelper helper;
    private Button createdb;
    private Button insertdata;
    private Button searchdata;
    private Button updatedata;
    private TextView textview;
    private ContentResolver resolver;
    private ContentValues values;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        createdb = (Button) findViewById(R.id.creare_content_database);
        insertdata = (Button) findViewById(R.id.add_content_data);
        searchdata = (Button) findViewById(R.id.qurey_content_data);
        updatedata = (Button) findViewById(R.id.updatel_content_database);
        textview = (TextView) findViewById(R.id.text);

        helper = new DBHelper((MainActivity3.this), ContentUtils.DB_NAME, null, 1);
        createdb.setOnClickListener(this);
        insertdata.setOnClickListener(this);
        searchdata.setOnClickListener(this);
        updatedata.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.creare_content_database:
                helper.getWritableDatabase();
                break;
            case R.id.add_content_data:
                SQLiteDatabase db1 = helper.getWritableDatabase();
                db1.execSQL("insert into book(id,name) values(?,?)", new String[]{"1", "lios"});
                break;
            case R.id.qurey_content_data:
                SQLiteDatabase db2 = helper.getWritableDatabase();
                Cursor cursor = db2.rawQuery("select * from book", null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    textview.setText("id:" + id + "name:" + name);
                }
                cursor.close();
                break;
            case R.id.updatel_content_database:
                resolver = this.getContentResolver();
                values = new ContentValues();
                values.put("id", 3);
                resolver.update(ContentUtils.CONTENT_URI, values, "name=?", new String[]{"lios"});
                //更新name="lios"的行id=3
                break;
            default:
                break;
        }
    }
}
