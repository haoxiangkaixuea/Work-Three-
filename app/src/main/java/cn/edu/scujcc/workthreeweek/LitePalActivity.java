package cn.edu.scujcc.workthreeweek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import cn.edu.scujcc.workthreeweek.data.local.MyDBHelper;
import cn.edu.scujcc.workthreeweek.util.LitePalUtils;

public class LitePalActivity extends AppCompatActivity {
    private MyDBHelper mydbHelper;
    private Button createDatabase;
    private Button addDatabase;
    private Button updateDatabase;
    private Button queryDatabase;
    private Button deteleDatabase;
    private TextView setText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        setText = findViewById(R.id.set_text);

        createDatabase = findViewById(R.id.creare_litepal_database);
        createDatabase.setOnClickListener(v -> {
            LitePal.getDatabase();
        });

        addDatabase = findViewById(R.id.add_litepal_data);
        addDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.addData();
        });

        updateDatabase = findViewById(R.id.update_litepal_database);
        updateDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.updateData();
        });

        deteleDatabase = findViewById(R.id.detele_litepal_database);
        deteleDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.deteleData();
        });

        queryDatabase = findViewById(R.id.qurey_litepal_data);
        queryDatabase.setOnClickListener(v -> {
            LitePalUtils litePalUtils = new LitePalUtils();
            litePalUtils.queryData();
        });
    }
}