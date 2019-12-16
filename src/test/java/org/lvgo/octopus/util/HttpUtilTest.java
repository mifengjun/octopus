package org.lvgo.octopus.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpUtilTest {

    @Test
    void get() {
        String s = HttpUtil.get("http://www.89ip.cn/tqdl.html?api=1&num=30&port=&address=&isp=");
        System.out.println("s = " + s);
    }
}