package cn.edu.scujcc.workthreeweek;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class ConstactsActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String> contactsLists = new ArrayList<>();
    private ListView contactsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constacts);

        contactsListView = findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<>(this, R.layout.listview_item, contactsLists);
        contactsListView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToFirst()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsLists.add(displayName + "\n" + number);
                }
                //适配器刷新
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    protected void onRequestPermissionsResultt(int requestCode, String[] permissions, int[] grandResults) {
        switch (requestCode) {
            case 1:
                if (grandResults.length > 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(this, "你需要开通权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}