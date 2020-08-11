package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DatasContentActivity extends AppCompatActivity {
    private Button btnDatasInsert;
    private Button btnDatasQuery;
    private Uri uri;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datas_content);

        btnDatasInsert = findViewById(R.id.add_content_datas);
        btnDatasQuery = findViewById(R.id.qurey_content_datas);
        btnDatasInsert.setOnClickListener(v -> {
            uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
            ContentValues values = new ContentValues();
            values.put("name", "百万英镑");
            values.put("author", "马克吐温");
            values.put("price", "44.2");
            values.put("pages", "345245");
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);
            Toast.makeText(DatasContentActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnDatasQuery.setOnClickListener(v -> {
            uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Toast.makeText(DatasContentActivity.this,
                            author + name + pages + price,
                            Toast.LENGTH_SHORT).show();
                    System.out.println("query book:" + cursor.getInt(0) + " " + cursor.getString(1));
                }
            }
            cursor.close();
        });
    }
}