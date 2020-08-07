package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scujcc.workthreeweek.data.local.MyDBHelper;
import cn.edu.scujcc.workthreeweek.util.DataUtils;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {
    private MyDBHelper mydbHelper;
    private Button btnCreateDatabase;
    private Button btnAddDatabase;
    private Button btnUpdateDatabase;
    private Button btnQueryDatabase;
    private Button btnDeteleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        mydbHelper = new MyDBHelper(this, "BookStore", null, 2);
        btnCreateDatabase = findViewById(R.id.creare_database);
        btnCreateDatabase.setOnClickListener(v -> {
            mydbHelper.getWritableDatabase();
        });

        //添加数据
        btnAddDatabase = findViewById(R.id.add_data);
        btnAddDatabase.setOnClickListener(v -> {
            //插入数据
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.addData();
            //装入第二条数据
            dataUtil.addSecondData();
        });

        //更新数据
        btnUpdateDatabase = findViewById(R.id.updatel_database);
        btnUpdateDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.updatetData();
        });

        //删除数据
        btnDeteleDatabase = findViewById(R.id.detele_database);
        btnDeteleDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.deteleData();
        });

        //查询数据
        btnQueryDatabase = findViewById(R.id.qurey_data);
        btnQueryDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.qureyData();
        });
    }
}