package org.lvgo.octopus.core;

import org.lvgo.octopus.bean.OctopusProxy;

/**
 * Ip代理接口
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/17 9:46
 */
public interface IpProxy {

    /**
     * 初始化代理
     *
     * @param octopusProxy
     */
    void init(OctopusProxy octopusProxy);
}
