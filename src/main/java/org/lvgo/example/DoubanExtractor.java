package org.lvgo.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusBeans;
import org.lvgo.octopus.bean.OctopusData;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.Octopus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 豆瓣解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/11 14:15
 */
public class DoubanExtractor extends OctopusBeans implements Extractor {

    @Override
    public void extract(Octopus octopus) {
        if (octopus.isSuccess()) {
            OctopusData octopusData = octopus.getOctopusData();
            octopusData.setTableName("豆瓣评论");
            Document document = octopus.getDocument();
            // 获取评论上下文
            Elements h2 = document.getElementsByTag("H2");
            if (h2.isEmpty()) {
                return;
            }
            // 并发处理
            concurrentHandle(octopus, h2);

        } else {
            log.error("请求失败,{}", octopus);
        }
    }

    /**
     * 解析评论详情
     *
     * @param octopus 上下文
     * @param element 当前数据
     */
    @Override
    public void elementHandle(Octopus octopus, Element element) {
        OctopusData octopusData = octopus.getOctopusData();
        List<Map<String, Object>> dataList = octopusData.getDataList();
        Map<String, Object> data = new HashMap<String, Object>(2);
        String href = element.getElementsByTag("a").first().attr("href");
        Document commentDetail = octopus.connect(href).getDocument();
        if (commentDetail != null) {
            String text = commentDetail.getElementsByClass("review-content").first().text();
            data.put("comment", text);
            String attr = commentDetail.getElementById("review-content").attr("data-ad-ext");

            String[] worth = attr.split("·");

            String use = worth[0].trim().substring(2);
            String unuse = worth[1].trim().substring(2);

            data.put("use", use);
            data.put("unuse", unuse);
            dataList.add(data);
        } else {
            log.error(href + " is error!!");
        }
    }

    /**
     * 获取影评基本信息
     *
     * @param document 上下文
     */
    private void getBaseInfo(Document document) {

        // todo: 填到你想放的任何地方

        String title = document.getElementsByTag("title").text();
        log.info("title : " + title.substring(0, title.indexOf("(")));
        Element drop = document.getElementsByClass("droplist").first();
        String rating = drop.getElementsByClass("rating").first().text();
        log.info("全部评论 : " + rating.substring(rating.indexOf("(") + 1, rating.length() - 1));
        String rating5 = drop.getElementsByClass("rating5").first().text();
        log.info("5星评论 : " + rating5.substring(rating5.indexOf("(") + 1, rating5.length() - 1));
        String rating4 = drop.getElementsByClass("rating4").first().text();
        log.info("4星评论 : " + rating4.substring(rating4.indexOf("(") + 1, rating4.length() - 1));
        String rating3 = drop.getElementsByClass("rating3").first().text();
        log.info("3星评论 : " + rating3.substring(rating3.indexOf("(") + 1, rating3.length() - 1));
        String rating2 = drop.getElementsByClass("rating2").first().text();
        log.info("2星评论 : " + rating2.substring(rating2.indexOf("(") + 1, rating2.length() - 1));
        String rating1 = drop.getElementsByClass("rating1").first().text();
        log.info("1星评论 : " + rating1.substring(rating1.indexOf("(") + 1, rating1.length() - 1));
    }

    /**
     * 获取总记录页数
     *
     * @param octopus 连接器(复制机)
     * @return 分页信息
     */
    @Override
    public OctopusPage getPageInfo(Octopus octopus) {
        OctopusPage octopusPage = new OctopusPage();

        getBaseInfo(octopus.getDocument());

        int page = octopus.getPage();
        int pageSize = octopus.getPageSize();

        int totalPage = Integer.valueOf(octopus.getDocument()
                .getElementsByClass("thispage").first()
                .attr("data-total-page"));

        octopusPage.setPageTotal(totalPage);

        // 组装分页地址
        List<String> urls = new ArrayList<>();
        // 将源地址补充到地址池
        urls.add(octopus.getUrl());
        // 通过分页大小获取分页数据
        for (int i = 1; i < (page != 0 ? page : totalPage); i++) {
            String url = octopus.getUrl() + "?start=" + pageSize * i;
            urls.add(url);
        }

        octopusPage.setUrls(urls);
        return octopusPage;
    }
}
