package cn.edu.scujcc.workthreeweek;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class DatesContentActivity extends AppCompatActivity {
    private Button btuDabsInsert;
    private Button btuDabsQuery;
    private Button btuDabsDelete;
    private Uri uri;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_content);

        btuDabsInsert = findViewById(R.id.add_content_dates);
        btuDabsQuery = findViewById(R.id.qurey_content_dates);
        btuDabsDelete = findViewById(R.id.delete_content_dates);
        btuDabsInsert.setOnClickListener(v -> {
            uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
            ContentValues values = new ContentValues();
            values.put("name", "百万英镑");
            values.put("author", "马克吐温");
            values.put("price", "44.2");
            values.put("pages", "345245");
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);
            Toast.makeText(DatesContentActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btuDabsQuery.setOnClickListener(v -> {
            uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Toast.makeText(DatesContentActivity.this,
                            "query Book:" + cursor.getInt(0) + " " + cursor.getString(1) + author + name + pages + price,
                            Toast.LENGTH_SHORT).show();
                }
            }
            cursor.close();
        });

        btuDabsDelete.setOnClickListener(v -> {
            uri = Uri.parse("content://cn.edu.scujcc.workthreeweek/book" + newId);
            getContentResolver().delete(uri, null, null);
            Toast.makeText(DatesContentActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });
    }
}