package org.lvgo.weboctopus.extractor;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.core.Extractor;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.weboctopus.common.GeneralConstant;
import org.lvgo.weboctopus.movie.bean.Comment;
import org.lvgo.weboctopus.movie.mapper.CommentMapper;
import org.lvgo.weboctopus.movie.mapper.MovieMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 豆瓣解析器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/14 10:32
 */
@Slf4j
@Component
public class DouBanExtractor implements Extractor {

    @Resource
    private MovieMapper movieMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void extract(Octopus octopus) {
        if (octopus.isSuccess()) {
            Document document = octopus.getDocument();
            // 获取评论上下文
            Elements comment = document.getElementsByTag("H2");
            if (comment.isEmpty()) {
                return;
            }
            // 并发处理
            concurrentHandle(octopus, comment);

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
        try {
            Comment comment = new Comment();
            comment.setDataTime(LocalDateTime.now());
            comment.setSource(GeneralConstant.SOURCE_DOU_BAN);

            String href = element.getElementsByTag("a").first().attr("href");
            Document commentDetail = octopus.connect(href).getDocument();


            if (commentDetail != null) {
                Element header = commentDetail.getElementsByTag("header").first();

                String commentPeople = header.getElementsByTag("a").get(0).attr("href");
                comment.setCommentPeople(commentPeople);
                String movie = header.getElementsByTag("a").get(1).attr("href");
                movie = movie.substring(0, movie.length() - 1);
                movie = movie.substring(movie.lastIndexOf("/") + 1);
                comment.setMovieId(movie);

                // 评论头内容, 存在于4个span中, 如果span的size为2时, 说明该条评论来自豆瓣APP
                Elements headerSpan = header.getElementsByTag("span");
                if (headerSpan.size() == 2) {
                    Element commentTime = header.getElementsByTag("span").get(1);
                    comment.setCommentDate(commentTime.text());
                } else {
                    String star = header.getElementsByTag("span").get(2).text();
                    comment.setCommentRating(star);

                    Element commentTime = header.getElementsByTag("span").get(3);
                    comment.setCommentDate(commentTime.text());
                }

                String text = commentDetail.getElementsByClass("review-content").first().text();
                comment.setComment(text);

                String attr = commentDetail.getElementById("review-content").attr("data-ad-ext");
                String[] worth = attr.split("·");
                String valuable = worth[0].trim().substring(2);
                String worthless = worth[1].trim().substring(2);

                comment.setValuable(Integer.valueOf(valuable));
                comment.setWorthless(Integer.valueOf(worthless));

                commentMapper.insert(comment);
            } else {
                log.error(href + " is error!!");
            }
        } catch (Exception e) {
            log.error("数据出现异常 : {}, 数据地址 : {}", e.getMessage(), octopus.getDoUrl());
        }

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

        int page = octopus.getPage();
        int pageSize = octopus.getPageSize();

        int totalPage = Integer.parseInt(octopus.getDocument()
                .getElementsByClass("thispage").first()
                .attr("data-total-page"));

        octopusPage.setPageTotal(totalPage);

        // 组装分页地址
        List<String> urls = new ArrayList<>();
        // 将源地址补充到地址池
        urls.add(octopus.getBaseUrl());
        // 通过分页大小获取分页数据
        for (int i = 1; i < (page != 0 ? page : totalPage); i++) {
            String url = octopus.getBaseUrl() + "?start=" + pageSize * i;
            urls.add(url);
        }

        octopusPage.setUrls(urls);
        return octopusPage;
    }

}
