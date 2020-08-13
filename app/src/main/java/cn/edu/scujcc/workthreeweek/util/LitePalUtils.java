package cn.edu.scujcc.workthreeweek.util;

import org.litepal.LitePal;

import java.util.List;

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
public class LitePalUtils {

    public LitePalUtils() {

    }

    public void addData() {
        //name和duration是你要存入的重复的数据
        BookModel bookModel = new BookModel();
        bookModel.setName("悲惨世界");
        bookModel.setAuthor("雨果");
        bookModel.setPages(52554);
        bookModel.setPrice(43.5);
        bookModel.save();
    }

    public void updateData() {
        BookModel bookModel = new BookModel();
        bookModel.setPrice(10.9);
        bookModel.updateAll("name= ?", "悲惨世界");
    }

    public void deleteData() {
        LitePal.deleteAll(BookModel.class, "price > ?", "40");
    }

    public void queryData() {
        List<BookModel> firstBook = LitePal.findAll(BookModel.class);
    }
}
