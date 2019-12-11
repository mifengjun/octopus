package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Data;
import org.lvgo.octopus.core.Octopus;

class DoubanExtractorTest {

    @Test
    void extract() {
        // 少年的你影评
        String movieId = "30166972";


        // 星级及页数参数 ?rating= 全部评论, ?rating=5 5星评论 ?rating=4 4星评论
        String starParam = "?rating=";

        // 翻页参数  每页20条, 递增
        String pageParam = "start=20";

        String url = "https://movie.douban.com/subject/" + movieId + "/reviews";
//        String url = "https://movie.douban.com/subject/30166972/reviews?rating=&start=40";

        Data extract = Octopus.init().url(url).get().extractor(new DoubanExtractor()).pageDown(true).pageSize(20).start();
    }
}