package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;

class DoubanExtractorTest {

    @Test
    void extract() {
        // 少年的你影评
//        String movieId = "30166972";
        // 机器人总动员
        String movieId = "2131459";

        String url = "https://movie.douban.com/subject/" + movieId + "/reviews";
        Octopus.init()
                .url(url)
                .get()
                .extractor(new DoubanExtractor())
                .page(1)
                .pageDown(true)
                .pageSize(20)
                .threads(1)
                .start();
    }
}