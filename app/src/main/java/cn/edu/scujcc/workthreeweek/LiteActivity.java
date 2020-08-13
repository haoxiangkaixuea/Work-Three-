package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.List;

import cn.edu.scujcc.workthreeweek.data.model.BookModel;

/**
 * @author Administrator
 */
public class LiteActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreateData;
    private Button btnAddData;
    private Button btnUpdateData;
    private Button btnQueryData;
    private Button btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite);

        btnCreateData = findViewById(R.id.create_database);
        btnAddData = findViewById(R.id.add_data);
        btnUpdateData = findViewById(R.id.update_data);
        btnQueryData = findViewById(R.id.query_data);
        btnDeleteData = findViewById(R.id.delete_data);
        btnCreateData.setOnClickListener(this);
        btnAddData.setOnClickListener(this);
        btnUpdateData.setOnClickListener(this);
        btnQueryData.setOnClickListener(this);
        btnDeleteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_database:
                LitePal.getDatabase();
                Toast.makeText(LiteActivity.this,
                        getResources().getString(R.string.create_database),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_data:
                BookModel bookModel = new BookModel();
                bookModel.setName("百万英镑");
                bookModel.setAuthor("马克吐温");
                bookModel.setPages(52554);
                bookModel.setPrice(43.5);
                bookModel.save();
                Toast.makeText(LiteActivity.this,
                        getResources().getString(R.string.add_data),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_data:
                BookModel bookMod = new BookModel();
                bookMod.setPrice(10.9);
                bookMod.updateAll("name= ? and  price=?", "百万英镑", "43.5");
                Toast.makeText(LiteActivity.this,
                        getResources().getString(R.string.update_database),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.query_data:
                List<BookModel> books = LitePal.findAll(BookModel.class);
                for (BookModel book : books) {
                    Toast.makeText(LiteActivity.this,
                            getResources().getString(R.string.query_database) + book.getName() + book.getAuthor()
                                    + book.getPrice() + book.getPages(),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete_data:
                LitePal.deleteAll(BookModel.class, "price < ?", "40");
                Toast.makeText(LiteActivity.this,
                        getResources().getString(R.string.delete_database),
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}