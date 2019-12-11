package org.lvgo.example;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.core.Data;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.util.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 微博数据解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 16:48
 */
public class WeiBoExtractor implements Extractor {
    @Override
    public Data extract(Octopus octopus) {
        Data data = new Data();
        ArrayList<Map<String, Object>> datas = new ArrayList<>();
        if (octopus.isSuccess()) {
            Document document = octopus.getDocument();
            Elements scripts = document.getElementsByTag("script");
            for (Element script : scripts) {
                if (RegexUtil.beginWith("<script>FM.view\\(\\{\"ns\":\"pl.content.homeFeed.index\",\"domid\":\"Pl_Official_MyProfileFeed", script.toString())) {
                    String outerHtml = script.outerHtml();
                    outerHtml = outerHtml.substring(16, outerHtml.length() - 10);
                    JSONObject jsonObject = JSONObject.parseObject(outerHtml);
                    document = Jsoup.parse(jsonObject.getString("html"));
                    break;
                }
            }

            Elements wb_detail = document.getElementsByClass("WB_detail");

            wb_detail.forEach(detail -> {
                HashMap<String, Object> wbDetail = new HashMap<>();
                Elements content = detail.getElementsByClass("WB_text W_f14");
                wbDetail.put("content", content.text());

                Elements titles = detail.getElementsByClass("WB_from S_txt2");
                String[] title = titles.text().split(" ");
                wbDetail.put("dateTime", title[0] + " " + title[1]);
                datas.add(wbDetail);

            });

            data.setDataList(datas);
            return data;
        } else {
            return null;
        }
    }
}
