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
    private Button createDatabase;
    private Button addDatabase;
    private Button updateDatabase;
    private Button queryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建数据库
        mydbHelper = new MyDBHelper(this, "BookStore", null, 2);
        createDatabase = findViewById(R.id.creare_database);
        createDatabase.setOnClickListener(v -> {
            mydbHelper.getWritableDatabase();
        });

        //添加数据
        addDatabase = findViewById(R.id.add_data);
        addDatabase.setOnClickListener(v -> {
            //插入数据
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.addData();
            //装入第二条数据
            dataUtil.addSecondData();
        });

        //更新数据
        updateDatabase = findViewById(R.id.update_database);
        updateDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.updatetData();
        });

        //删除数据
        Button deteleDatabase = findViewById(R.id.detele_database);
        deteleDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.deteleData();
        });

        //查询数据
        queryDatabase = findViewById(R.id.qurey_data);
        queryDatabase.setOnClickListener(v -> {
            DataUtils dataUtil = new DataUtils(mydbHelper);
            dataUtil.qureyData();
        });
    }
}