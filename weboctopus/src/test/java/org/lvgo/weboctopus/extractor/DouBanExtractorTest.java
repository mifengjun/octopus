package org.lvgo.weboctopus.extractor;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.weboctopus.WebOctopusApplicationTests;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author lvgo
 * @version 1.0.0
 * @date 2020/5/16 22:15
 */
class DouBanExtractorTest extends WebOctopusApplicationTests {

    @Resource
    private DouBanExtractor douBanExtractor;

    @Test
    void extract() {

        String movieId = "30176393";
        String url = "https://movie.douban.com/subject/" + movieId + "/reviews";

        Octopus octopus = Octopus.init();
        octopus.url(url).extractor(douBanExtractor).pageDown(true).page(3).pageSize(20).start();
    }
}