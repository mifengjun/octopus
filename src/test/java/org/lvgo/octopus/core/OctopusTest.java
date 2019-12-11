package org.lvgo.octopus.core;

import org.junit.Test;

public class OctopusTest {

    @Test
    public void copy() {
        Octopus.url("http://www.baidu.com").copy();
    }

    @Test
    public void url() {
        Octopus.url("http://www.baidu.com");
    }

    @Test
    public void getUrl() {
    }
}