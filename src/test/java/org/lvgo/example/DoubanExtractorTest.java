package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.bean.OctopusProxy;
import org.lvgo.octopus.core.Octopus;

class DoubanExtractorTest {

    @Test
    void extract() {

        OctopusProxy octopusProxy = OctopusProxy.getInstance();


        octopusProxy.add("139.0.22.50", 8080);
        octopusProxy.add("45.7.238.250", 30980);
        octopusProxy.add("87.76.10.119", 4550);
        octopusProxy.add("103.9.188.229", 36984);
        octopusProxy.add("77.94.112.234", 32222);
        octopusProxy.add("170.80.151.172", 8080);
        octopusProxy.add("5.158.123.153", 3128);
        octopusProxy.add("150.95.131.174", 3128);
        octopusProxy.add("177.17.103.152", 8080);
        octopusProxy.add("180.183.131.234", 8080);
        octopusProxy.add("103.134.213.54", 8080);
        octopusProxy.add("118.99.103.10", 8080);
        octopusProxy.add("118.24.61.165", 8118);
        octopusProxy.add("43.225.164.59", 57416);
        octopusProxy.add("190.147.137.66", 59947);
        octopusProxy.add("123.57.61.38", 8118);
        octopusProxy.add("144.91.116.171", 80);
        octopusProxy.add("213.58.202.70", 54214);
        octopusProxy.add("103.83.174.40", 8080);
        octopusProxy.add("37.221.204.206", 47424);
        octopusProxy.add("200.84.164.53", 3128);
        octopusProxy.add("200.89.174.109", 8080);
        octopusProxy.add("78.81.245.153", 8080);
        octopusProxy.add("122.230.245.119", 9999);
        octopusProxy.add("158.140.140.57", 8888);
        octopusProxy.add("193.68.200.85", 32070);
        octopusProxy.add("27.111.43.17", 80);
        octopusProxy.add("177.87.79.90", 8081);
        octopusProxy.add("27.152.91.89", 9999);
        octopusProxy.add("159.203.78.148", 8888);


        // 少年的你影评
//        String movieId = "30166972";
        // 机器人总动员
        String movieId = "2131459";
        String url = "https://movie.douban.com/subject/" + movieId + "/reviews";
        Octopus.init()
                .url(url)
                .get()
                .extractor(new DoubanExtractor())
                .proxy(octopusProxy)
                .timeOut(2000)
                .pageSize(20)
                .threads(2)
                .start();
    }
}