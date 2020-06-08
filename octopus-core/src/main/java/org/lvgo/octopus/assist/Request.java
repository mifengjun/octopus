package org.lvgo.octopus.assist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 16:15
 */
public class Request {

    private Logger log = LoggerFactory.getLogger(Request.class);

    private AtomicInteger currentPage = new AtomicInteger(0);

    private String rootUrl;
    /**
     * 请求地址
     */
    private Queue<String> urlQueue = new LinkedBlockingQueue<>();

    public Request() {

    }

    public AtomicInteger getCurrentPage() {
        return currentPage;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public void putUrl(String url) {
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "https://" + url;
        }
        urlQueue.add(url);
        log.info("加入地址成功! 当前url队列大小:{}", urlQueue.size());
    }

    public String getUrl() {
        String url = urlQueue.poll();
        log.info("获取地址成功! 当前url队列大小:{}", urlQueue.size());
        return url;
    }
}
