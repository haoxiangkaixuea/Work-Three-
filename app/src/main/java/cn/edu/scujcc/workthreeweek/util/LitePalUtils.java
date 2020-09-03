package cn.edu.scujcc.workthreeweek.util;

import org.litepal.LitePal;

import java.util.List;

import cn.edu.scujcc.workthreeweek.data.model.BookModel;

/**
 * @author Administrator
 */
public class LitePalUtils {

    public LitePalUtils() {

    }

    public void addData() {
        BookModel bookModel = new BookModel();
        bookModel.setName("百万英镑");
        bookModel.setAuthor("马克吐温");
        bookModel.setPages(52554);
        bookModel.setPrice(43.5);
        bookModel.save();
    }

    public void updateData() {
        BookModel bookMod = new BookModel();
        bookMod.setPrice(10.9);
        bookMod.updateAll("name= ? and  price=?", "百万英镑", "43.5");
    }

    public void deleteData() {
        LitePal.deleteAll(BookModel.class, "price > ?", "40");
    }

    public void queryData() {
        List<BookModel> firstBook = LitePal.findAll(BookModel.class);
    }
}
