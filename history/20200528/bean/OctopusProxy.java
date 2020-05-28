package org.lvgo.octopus.bean;

import org.lvgo.octopus.core.IpProxy;

import java.util.ArrayList;
import java.util.List;

/**
 * 八爪鱼之IP代理
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 15:40
 */
public class OctopusProxy extends AbstractOctopusBean {

    private static final long serialVersionUID = 7028017492503703485L;
    /**
     * 章鱼代理
     */
    private volatile static OctopusProxy octopusProxy;
    /**
     * 代理ip
     */
    private String host;
    /**
     * 代理端口
     */
    private int port;

    public List<OctopusProxy> getOctopusProxies() {
        return octopusProxies;
    }

    /**
     * 代理IP列表
     */
    private List<OctopusProxy> octopusProxies;

    private OctopusProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private OctopusProxy() {
        octopusProxies = new ArrayList<>(100);
    }

    /**
     * 单例代理  通过双重检查锁来创建单例对象
     *
     * @return 八爪鱼代理
     */
    public static OctopusProxy getInstance(IpProxy ipProxy) {

        if (octopusProxy == null) {
            synchronized (OctopusProxy.class) {
                if (octopusProxy == null) {
                    octopusProxy = new OctopusProxy();
                }
            }
        }

        ipProxy.init(octopusProxy);
        return octopusProxy;
    }

    public boolean isEmpty() {
        return octopusProxies.isEmpty();
    }

    public void remove(OctopusProxy octopusProxy) {
        octopusProxies.remove(octopusProxy);
    }

    public void add(String host, int port) {
        octopusProxies.add(new OctopusProxy(host, port));
    }


    public OctopusProxy randomProxy() {
        int index = Math.toIntExact(Math.round(Math.random() * (octopusProxies.size() - 1)));
        OctopusProxy octopusProxy = octopusProxies.get(index);
        log.info("当前代理IP剩余 : " + octopusProxies.size() + "个");
        return octopusProxy;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "OctopusProxy{" + "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
