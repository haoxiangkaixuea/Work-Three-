package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private Button createDatabase;
    private MyDataabaseHelper mydbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        mydbHelper = new MyDataabaseHelper(this, "BookStore", null, 1);
        createDatabase = findViewById(R.id.creare_database);
        createDatabase.setOnClickListener(v -> {
            mydbHelper.getWritableDatabase();
        });
    }
}