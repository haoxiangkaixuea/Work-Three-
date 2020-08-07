package cn.edu.scujcc.workthreeweek.data.model;

import org.litepal.crud.LitePalSupport;

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
public class BookModel extends LitePalSupport {
    private int id;
    private String author;
    private String name;
    private Double price;
    private int pages;

    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
