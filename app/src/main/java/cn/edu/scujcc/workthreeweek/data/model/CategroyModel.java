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
 */
public class CategroyModel extends LitePalSupport {
    private int id;
    private String categroyName;
    private String categroyCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategroyName() {
        return categroyName;
    }

    public void setCategroyName(String categroyName) {
        this.categroyName = categroyName;
    }

    public String getCategroyCode() {
        return categroyCode;
    }

    public void setCategroyCode(String categroyCode) {
        this.categroyCode = categroyCode;
    }
}
