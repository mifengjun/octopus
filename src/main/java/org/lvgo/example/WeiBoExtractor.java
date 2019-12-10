package org.lvgo.example;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.core.Data;
import org.lvgo.octopus.core.Duplicate;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.util.RegexUtil;

/**
 * 微博数据解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 16:48
 */
public class WeiBoExtractor implements Extractor {
    @Override
    public Data extract(Duplicate duplicate) {
        Data data = new Data();
        if (duplicate.isSuccess()) {
            Document document = duplicate.getDocument();
            Elements scripts = document.getElementsByTag("script");
            for (Element script : scripts) {
                if (RegexUtil.beginWith("<script>FM.view\\(\\{\"ns\":\"pl.content.homeFeed.index\",\"domid\":\"Pl_Official_MyProfileFeed", script.toString())) {
                    String outerHtml = script.outerHtml();
                    outerHtml = outerHtml.substring(16, outerHtml.length() - 10);
                    JSONObject jsonObject = JSONObject.parseObject(outerHtml);
                    document = Jsoup.parse(jsonObject.getString("html"));
                }
            }

            Elements wb_detail = document.getElementsByClass("WB_detail");

            wb_detail.forEach(detail -> {


                Elements content = detail.getElementsByClass("WB_text W_f14");
                System.out.println(content.text());

                Elements time = detail.getElementsByClass("WB_from S_txt2");
                System.out.println(time.text() + "\n");
            });


            return data;
        } else {
            return null;
        }
    }
}
