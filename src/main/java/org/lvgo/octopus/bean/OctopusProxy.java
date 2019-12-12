package org.lvgo.octopus.bean;

import java.util.List;
import java.util.Random;

/**
 * 八爪鱼之IP代理
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 15:40
 */
public class OctopusProxy extends BaseBean {

    private String host;

    private int port;

    private List<OctopusProxy> octopusProxies;

    public OctopusProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public List<OctopusProxy> getOctopusProxies() {
        return octopusProxies;
    }

    public void setOctopusProxies(List<OctopusProxy> octopusProxies) {
        this.octopusProxies = octopusProxies;
    }

    public OctopusProxy randomProxy() {
        return octopusProxies.get(new Random(octopusProxies.size()).nextInt());
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
