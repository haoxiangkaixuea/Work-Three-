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
        List<BookModel> bookModels = LitePal.findAll(BookModel.class);
        for (int i = 0; i < bookModels.size(); i++) {
            if (bookModels.get(i).getName() == "悲惨世界"
                    && bookModels.get(i).getAuthor() == "雨果"
                    && bookModels.get(i).getPrice() == 52554
                    && bookModels.get(i).getPrice() == 43.5) {
                bookModels.get(i).delete();
            }
        }
        bookModel.setName("悲惨世界");
        bookModel.setAuthor("雨果");
        bookModel.setPages(52554);
        bookModel.setPrice(43.5);
        bookModel.save();
    }

    public void updateData() {
        BookModel bookModel = new BookModel();
        List<BookModel> bookModels = LitePal.findAll(BookModel.class);
        for (int i = 0; i < bookModels.size(); i++) {
            if (bookModels.get(i).getName() == "红楼梦"
                    && bookModels.get(i).getAuthor() == "曹雪芹"
                    && bookModels.get(i).getPages() == 233534
                    && bookModels.get(i).getPrice() == 35.9) {
                bookModels.get(i).delete();
            }
        }
        bookModel.setName("红楼梦");
        bookModel.setAuthor("曹雪芹");
        bookModel.setPages(233534);
        bookModel.setPrice(35.9);
        bookModel.save();

        bookModel.setPrice(10.9);
        bookModel.save();
        bookModel.updateAll("name= ? and author =?", "红楼梦", "曹雪芹");
    }

    public void deleteData() {
        LitePal.deleteAll(BookModel.class, "price< ?", "40");
    }

    public void queryData() {
        List<BookModel> firstBook = (List<BookModel>) LitePal.findFirst(BookModel.class);
    }
}
