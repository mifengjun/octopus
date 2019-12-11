package org.lvgo.octopus.core;

import org.junit.Test;

public class OctopusTest {

    @Test
    public void copy() {
        Octopus.init().url("http://www.baidu.com");
    }

    @Test
    public void url() {
        Octopus.init().url("http://www.baidu.com");
    }

    @Test
    public void getUrl() {
    }
}