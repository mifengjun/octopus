package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.bean.OctopusProxy;

class XiCiIpProxyTest {

    @Test
    void init() {
        OctopusProxy octopusProxy = OctopusProxy.getInstance(new XiCiIpProxy());
        System.out.println("octopusProxy = " + octopusProxy.getOctopusProxies());
    }
}