package org.lvgo.octopus;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.core.Octopus;

class OctopusTest {

    @Test
    void init() {
        Octopus.init("www.baidu.com").start();
    }
}