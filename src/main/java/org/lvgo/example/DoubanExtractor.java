package org.lvgo.example;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.core.Data;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.Octopus;

import java.util.ArrayList;
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
    public Data extract(Octopus octopus) {
        Data data = new Data();
        ArrayList<Map<String, Object>> datas = new ArrayList<>();
        if (octopus.isSuccess()) {

            String title = octopus.getDocument().getElementsByTag("title").text();
            System.out.println("title : " + title.substring(0, title.indexOf("(")));

            Elements droplist = octopus.getDocument().getElementsByClass("droplist");
            Element drop = droplist.first();

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


            Elements h2 = octopus.getDocument().getElementsByTag("H2");
            h2.forEach(comment -> {
                String href = comment.getElementsByTag("a").first().attr("href");


                Document commentDetail = octopus.url(href).get().getDocument();
                String text = commentDetail.getElementsByClass("review-content").first().text();
                System.out.println("text = " + text);
                String attr = commentDetail.getElementById("review-content").attr("data-ad-ext");
                System.out.println("attr = " + attr);
                System.out.println("---------------------------------------------------------");

            });


            return data;
        } else {
            return null;
        }
    }
}
