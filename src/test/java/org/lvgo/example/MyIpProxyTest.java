package org.lvgo.example;

import org.junit.jupiter.api.Test;
import org.lvgo.octopus.bean.OctopusProxy;

import static org.junit.jupiter.api.Assertions.*;

class MyIpProxyTest {

    @Test
    void init() {
        MyIpProxy myIpProxy = new MyIpProxy();
        myIpProxy.init(null);
    }
}