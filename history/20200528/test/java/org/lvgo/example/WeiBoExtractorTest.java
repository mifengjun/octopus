package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;

import java.util.HashMap;

class WeiBoExtractorTest {

    @Test
    void extract() {
//        String url = "https://weibo.com/u/5537039775";
        String url = "https://weibo.com/u/2802067862";
//        String url = "https://s.weibo.com/weibo/%25E4%25B8%258A%25E5%25AD%25A6?topnav=1&wvr=6&b=1";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Host", "weibo.com");
        Octopus.init().proxy(new XiCiIpProxy()).url(url)
                .headers(headers).get().extractor(new WeiBoExtractor()).start();
    }
}