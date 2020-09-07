package cn.edu.scujcc.workthreeweek.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.List;

import cn.edu.scujcc.workthreeweek.R;
import cn.edu.scujcc.workthreeweek.data.model.BookModel;
import cn.edu.scujcc.workthreeweek.util.LitePalUtils;

/**
 * @author Administrator
 */
public class LiteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite);

        Button btnCreateData = findViewById(R.id.create_database);
        Button btnAddData = findViewById(R.id.add_data);
        Button btnUpdateData = findViewById(R.id.update_data);
        Button btnQueryData = findViewById(R.id.query_data);
        Button btnDeleteData = findViewById(R.id.delete_data);
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
                LitePalUtils addLitePalUtils = new LitePalUtils();
                addLitePalUtils.addData();
                Toast.makeText(LiteActivity.this,
                        getResources().getString(R.string.add_data),
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_data:
                LitePalUtils updateLitePalUtils = new LitePalUtils();
                updateLitePalUtils.updateData();
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