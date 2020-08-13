package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.List;

import cn.edu.scujcc.workthreeweek.data.model.BookModel;

/**
 * @author Administrator
 */
public class LitePalActivity extends AppCompatActivity {
    private Button btnCreateDatabase;
    private Button btnAddDatabase;
    private Button btnUpdateDatabase;
    private Button btnQueryDatabase;
    private Button btnDeleteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        btnCreateDatabase = findViewById(R.id.create_lite_database);
        btnCreateDatabase.setOnClickListener(v -> {
            LitePal.getDatabase();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.create_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnAddDatabase = findViewById(R.id.add_lite_data);
        btnAddDatabase.setOnClickListener(v -> {
            BookModel bookModel = new BookModel();
            bookModel.setName("悲惨世界");
            bookModel.setAuthor("雨果");
            bookModel.setPages(52554);
            bookModel.setPrice(43.5);
            bookModel.save();
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.add_data),
                    Toast.LENGTH_SHORT).show();
        });

        btnUpdateDatabase = findViewById(R.id.update_lite_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            BookModel bookModel = new BookModel();
            bookModel.setPrice(50.0);
            bookModel.updateAll("name= ?", "悲惨世界");
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.update_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnDeleteDatabase = findViewById(R.id.delete_lite_database);
        btnDeleteDatabase.setOnClickListener(v -> {
            LitePal.deleteAll(BookModel.class, "price > ?", "40");
            Toast.makeText(LitePalActivity.this,
                    getResources().getString(R.string.delete_database),
                    Toast.LENGTH_SHORT).show();
        });

        btnQueryDatabase = findViewById(R.id.query_lite_data);
        btnQueryDatabase.setOnClickListener(v -> {
            List<BookModel> books = (List<BookModel>) LitePal.findFirst(BookModel.class);
            for (BookModel book : books) {
                Toast.makeText(LitePalActivity.this,
                        getResources().getString(R.string.query_database) + book.getName() + book.getAuthor()
                                + book.getPrice() + book.getPages(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}