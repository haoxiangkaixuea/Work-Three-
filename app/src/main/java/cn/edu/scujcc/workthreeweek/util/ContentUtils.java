package cn.edu.scujcc.workthreeweek.util;

import android.net.Uri;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/09
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */
public class ContentUtils {
    public static final String AUTOHORITY = "cn.edu.scujcc.workthreeweek";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/book");
    public static String DB_NAME = "BOOKDB";
}
