package org.lvgo.octopus.core;


import org.junit.jupiter.api.Test;

class OctopusTest {

    @Test
    void copy() {
        Octopus.init().url("http://www.baidu.com");
    }

    @Test
    void url() {
        Octopus.init().url("http://www.baidu.com");
    }

    @Test
    void getUrl() {
    }
}