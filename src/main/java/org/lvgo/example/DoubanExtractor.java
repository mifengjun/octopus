package org.lvgo.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.silent.TaskHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 豆瓣解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/11 14:15
 */
public class DoubanExtractor implements Extractor {

    @Override
    public void extract(Octopus octopus) {
        ArrayList<Map<String, Object>> datas = new ArrayList<>();
        if (octopus.isSuccess()) {

            Document document = octopus.getDocument();
            // 获取评论上下文
            Elements h2 = document.getElementsByTag("H2");
            if (h2.isEmpty()) {
                return;
            }

            // 线程数大于0时, 启动多线程处理
            new TaskHandler<Element>(h2) {
                @Override
                public void run(Element comment) {
                    commentDetail(octopus, comment);
                }
            }.sync(true).execute(octopus.getPageSize() > 1 ? octopus.getPageSize() : 1);
        } else {
            //TODO:失败情况处理
        }
    }

    /**
     * 获取影评基本信息
     *
     * @param document 上线文
     */
    private void getBaseInfo(Document document) {
        String title = document.getElementsByTag("title").text();
        System.out.println("title : " + title.substring(0, title.indexOf("(")));
        Element drop = document.getElementsByClass("droplist").first();
        String rating = drop.getElementsByClass("rating").first().text();
        System.out.println("全部评论 : " + rating.substring(rating.indexOf("(") + 1, rating.length() - 1));
        String rating5 = drop.getElementsByClass("rating5").first().text();
        System.out.println("5星评论 : " + rating5.substring(rating5.indexOf("(") + 1, rating5.length() - 1));
        String rating4 = drop.getElementsByClass("rating4").first().text();
        System.out.println("4星评论 : " + rating4.substring(rating4.indexOf("(") + 1, rating4.length() - 1));
        String rating3 = drop.getElementsByClass("rating3").first().text();
        System.out.println("3星评论 : " + rating3.substring(rating3.indexOf("(") + 1, rating3.length() - 1));
        String rating2 = drop.getElementsByClass("rating2").first().text();
        System.out.println("2星评论 : " + rating2.substring(rating2.indexOf("(") + 1, rating2.length() - 1));
        String rating1 = drop.getElementsByClass("rating1").first().text();
        System.out.println("1星评论 : " + rating1.substring(rating1.indexOf("(") + 1, rating1.length() - 1));
    }

    /**
     * 解析评论详情
     *
     * @param octopus 抓取上下文信息
     * @param comment 评论数据源码
     */
    private void commentDetail(Octopus octopus, Element comment) {
        String href = comment.getElementsByTag("a").first().attr("href");
        Document commentDetail = octopus.connect(href).getDocument();
        if (commentDetail != null) {
            String text = commentDetail.getElementsByClass("review-content").first().text();
            System.out.println("text = " + text);
            String attr = commentDetail.getElementById("review-content").attr("data-ad-ext");
            System.out.println("attr = " + attr);
        } else {
            System.out.println(href + " is error!!");
        }
        System.out.println("---------------------------------------------------------");
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
