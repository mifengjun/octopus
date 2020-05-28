package org.lvgo.weboctopus.extractor;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.core.MovieExtractor;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.weboctopus.common.GeneralConstant;
import org.lvgo.weboctopus.movie.entity.Comment;
import org.lvgo.weboctopus.movie.entity.Movie;
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
public class DouBanExtractor implements MovieExtractor {

    @Resource
    private MovieMapper movieMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void fetchMovieInfo(Octopus octopus) {
        // 抓取电影信息, 这里只需要抓一次电影的信息即可
        Document document = octopus.getDocument();
        Element movieInfo = document.getElementById("info");
        String movieId = octopus.getParam("movieId");
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        // 数据获取时间
        movie.setDataTime(LocalDateTime.now());

        Elements nameInfo = document.getElementsByTag("H1").first().getElementsByTag("span");
        // 影片名称
        movie.setMovieName(nameInfo.get(0).text());
        // 影片发行时间
        movie.setInitialReleaseDate(nameInfo.get(1).text());

        // 这里会有10个p1, 根据p1获取
        Elements p1 = movieInfo.getElementsByClass("p1");

        // 发行国家
//        movie.setProducerCountry();

        // 数据来源
        movie.setSource("douban");

        if (movieMapper.selectById(movieId) == null) {
            movieMapper.insert(movie);
        } else {
            movieMapper.updateById(movie);
        }
    }


    @Override
    public void extract(Octopus octopus) {
        if (octopus.isSuccess()) {
            Document document = octopus.getDocument();
            // 获取评论上下文
            Elements comments = document.getElementsByTag("H2");
            if (comments.isEmpty()) {
                return;
            }
            // 并发处理
            concurrentHandle(octopus, comments);

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
            comment.setMovieId(octopus.getParam("movieId"));
            // 数据抓取时间
            comment.setDataTime(LocalDateTime.now());
            // 数据来源
            comment.setSource(GeneralConstant.SOURCE_DOU_BAN);

            String href = element.getElementsByTag("a").first().attr("href");
            Document commentDetail = octopus.connect(href).getDocument();


            if (commentDetail != null) {
                Element header = commentDetail.getElementsByTag("header").first();
                String commentId = commentDetail.getElementsByClass("main").first().attr("id");

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

                String text = commentDetail.getElementsByClass("review-content").first().text();
                // 评论内容
                comment.setComment(text);

                String attr = commentDetail.getElementById("review-content").attr("data-ad-ext");
                String[] worth = attr.split("·");
                String valuable = worth[0].trim().substring(2);
                String worthless = worth[1].trim().substring(2);

                // 有用值
                comment.setValuable(Integer.valueOf(valuable));
                // 无用值
                comment.setWorthless(Integer.valueOf(worthless));

                // 如果数据存在进行更新
                if (commentMapper.selectById(commentId) == null) {
                    commentMapper.insert(comment);
                } else {
                    commentMapper.updateById(comment);
                }
            } else {
                log.error(href + " 抓取内容为空!!");
            }
        } catch (Exception e) {
            log.error("数据出现异常, 数据地址 : {}", octopus.getDoUrl(), e);
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
