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
public class CategoryModel extends LitePalSupport {
    private int id;
    private String categoryName;
    private String categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
