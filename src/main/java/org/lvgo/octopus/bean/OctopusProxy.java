package org.lvgo.octopus.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 八爪鱼之IP代理
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 15:40
 */
public class OctopusProxy extends BaseBean {

    /**
     * 章鱼锁
     */
    private static final ReentrantLock octopusLock = new ReentrantLock();
    /**
     * 章鱼代理
     */
    private static OctopusProxy octopusProxy = new OctopusProxy();
    /**
     * 代理ip
     */
    private String host;
    /**
     * 代理端口
     */
    private int port;
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
     * 单例代理
     *
     * @return 八爪鱼代理
     */
    public static OctopusProxy getInstance() {
        if (octopusProxy == null) {
            octopusLock.lock();
            try {
                if (octopusProxy == null) {
                    return new OctopusProxy();
                } else {
                    return octopusProxy;
                }
            } finally {
                octopusLock.unlock();
            }
        } else {
            return octopusProxy;
        }
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
//        octopusProxies.remove(octopusProxy);
        // TODO: 当代理IP发生请求失败时, 从代理列表中移除
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
        final StringBuilder sb = new StringBuilder("OctopusProxy{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
