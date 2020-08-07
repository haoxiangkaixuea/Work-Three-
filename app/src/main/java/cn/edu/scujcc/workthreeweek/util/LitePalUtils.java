package cn.edu.scujcc.workthreeweek.util;

import android.widget.TextView;

import org.litepal.LitePal;

import java.util.List;

import cn.edu.scujcc.workthreeweek.LitePalActivity;
import cn.edu.scujcc.workthreeweek.R;
import cn.edu.scujcc.workthreeweek.data.model.BookModel;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/06
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class LitePalUtils extends LitePalActivity {
    private TextView setText;

    public void addData() {
        setText = findViewById(R.id.set_text);
        BookModel bookModel = new BookModel();
        bookModel.setName("悲惨世界");
        bookModel.setAuthor("雨果");
        bookModel.setPages(52554);
        bookModel.setPrice(43.5);
        bookModel.save();
        setText.setText("你添加了数据为：" + bookModel.toString());
    }

    public void updateData() {
        BookModel bookModel = new BookModel();
        bookModel.setName("红楼梦");
        bookModel.setAuthor("曹雪芹");
        bookModel.setPages(233534);
        bookModel.setPrice(35.9);
        bookModel.save();
        bookModel.setPrice(10.9);
        bookModel.save();
        bookModel.updateAll("name= ? and author =?", "红楼梦", "曹雪芹");
        setText.setText("你成功更新了数据");
    }

    public void deteleData() {
        LitePal.deleteAll(BookModel.class, "price< ?", "40");
        setText.setText("你已经删除了数据");
    }

    public void queryData() {
        List<BookModel> bookModels = LitePal.findAll(BookModel.class);
        for (BookModel bookModel : bookModels) {
            setText.setText("name" + bookModel.getName()
                    + "author" + bookModel.getAuthor()
                    + "price" + bookModel.getPrice()
                    + "pages" + bookModel.getPages());
        }
    }
}
