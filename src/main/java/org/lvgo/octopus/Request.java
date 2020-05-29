package org.lvgo.octopus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 请求载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 16:15
 */
public class Request {

    private Logger log = LoggerFactory.getLogger(Request.class);

    /**
     * 请求地址
     */
    private Queue<String> urlQueue = new LinkedBlockingQueue<>();
    /**
     * 请求方式
     */
    private String method = OctopusConstants.REQUEST_METHOD_GET;


    public Request() {

    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void putUrl(String url) {
        urlQueue.add(url);
        log.info("当前url队列大小:{}", urlQueue.size());
    }

    public String getUrl() {
        String url = urlQueue.poll();
        log.info("当前url队列大小:{}", urlQueue.size());
        return url;
    }
}
