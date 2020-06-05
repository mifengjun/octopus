package org.lvgo.octopus;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 豆瓣网页解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 17:17
 */
public class DouBanParser implements Parser {
    private Logger log = LoggerFactory.getLogger(DouBanParser.class);

    @Override
    public Data parse(Request request, Response response) {
        Document document = response.getDocument();
        Element reviewList = document.getElementsByClass("review-list").first();

        // 如果评论列表不为空, 就将所有的评论详情url放入队列中
        if (reviewList != null) {
            Elements reviewItems = reviewList.getElementsByClass("review-item");
            for (Element reviewItem : reviewItems) {
                String url = reviewItem.getElementsByTag("H2").first().getElementsByTag("a").first().attr("href");
                request.putUrl(url);
            }

            String pageUrl = request.getRootUrl() + "?start=" + request.getCurrentPage().incrementAndGet() * 20;
            log.info("加入下一页地址到地址队列 : {}", pageUrl);
            request.putUrl(pageUrl);

        } else {
            Comment comment = new Comment();
            Element header = document.getElementsByTag("header").first();
            String commentId = document.getElementsByClass("main").first().attr("id");

            // 评论id
            comment.setCommentId(commentId);
            String commentPeople = header.getElementsByTag("a").get(0).attr("href");
            // 评论人地址
            comment.setCommentPeople(commentPeople);

            // 评论头内容, 存在于4个span中, 如果span的size为2时, 说明该条评论来自豆瓣APP
            Elements headerSpan = header.getElementsByTag("span");
            if (headerSpan.size() == 2) {
                Element commentTime = header.getElementsByTag("span").get(1);
                // 评论日期
                comment.setCommentDate(commentTime.text());
            } else {
                String star = header.getElementsByTag("span").get(2).text();
                // 评论星级
                comment.setCommentRating(star);

                Element commentTime = header.getElementsByTag("span").get(3);
                // 评论日期
                comment.setCommentDate(commentTime.text());
            }

            String text = document.getElementsByClass("review-content").first().text();
            // 评论内容
            comment.setComment(text);

            String attr = document.getElementById("review-content").attr("data-ad-ext");
            String[] worth = attr.split("·");
            String valuable = worth[0].trim().substring(2);
            String worthless = worth[1].trim().substring(2);

            // 有用值
            comment.setValuable(Integer.valueOf(valuable));
            // 无用值
            comment.setWorthless(Integer.valueOf(worthless));

        }
        return null;
    }
}
