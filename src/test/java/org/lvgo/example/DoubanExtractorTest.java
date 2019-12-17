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

        Octopus octopus = Octopus.init();

        octopus.url(url).get().extractor(new DoubanExtractor()).pageSize(20)
                .start();

        System.out.println(octopus.getOctopusData());
    }
}