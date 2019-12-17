package org.lvgo.octopus.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilTest {

    @Test
    void get() {

        String url = "http://www.89ip.cn/index.html";

        String s = HttpUtil.get(url);
        System.out.println("s = " + s);
    }
}