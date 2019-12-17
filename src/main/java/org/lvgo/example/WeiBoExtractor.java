package org.lvgo.example;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusBeans;
import org.lvgo.octopus.bean.OctopusData;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.octopus.util.RegexUtil;
import org.lvgo.silent.TaskHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 微博数据解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 16:48
 */
public class WeiBoExtractor extends OctopusBeans implements Extractor {

    @Override
    public OctopusPage getPageInfo(Octopus octopus) {
        return null;
    }

    @Override
    public void extract(Octopus octopus) {
        OctopusData octopusData = new OctopusData();
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

            Elements wbDetail = document.getElementsByClass("WB_detail");

            new TaskHandler<Element>(wbDetail) {
                @Override
                public void run(Element detail) {
                    getWbDetail(datas, detail);
                }
            }.sync(true).execute(octopus.getThreadSize() > 1 ? octopus.getThreadSize() : 1);


            octopusData.setDataList(datas);

        } else {
        }

        log.info(Arrays.toString(octopusData.getDataList().toArray()));
    }

    @Override
    public void elementHandle(Octopus octopus, Element element) {

    }

    private void getWbDetail(ArrayList<Map<String, Object>> datas, Element detail) {
        HashMap<String, Object> wbdetail = new HashMap<>(2);
        Elements content = detail.getElementsByClass("WB_text W_f14");
        wbdetail.put("content", content.text());

        Elements titles = detail.getElementsByClass("WB_from S_txt2");
        String[] title = titles.text().split(" ");
        wbdetail.put("dateTime", title[0] + " " + title[1]);
        datas.add(wbdetail);
    }
}
