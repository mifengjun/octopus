package org.lvgo.weboctopus;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;

import java.util.HashMap;

/**
 * 普通测试类
 *
 * @author lvgorice@gmail.com
 * @date 2020/5/16 23:17
 * @since 1.0.0
 */
class NormalTest {


    @Test
    void requestTest() {
//        String url = "https://movie.douban.com/top250";
        String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=热门&sort=recommend&page_limit=20&page_start=0";

        Document document = Octopus.init().connect(url).getDocument();
        System.out.println("document = " + document.getElementsByTag("body").first().text());
    }
}
