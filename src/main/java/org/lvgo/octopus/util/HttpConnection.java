package org.lvgo.octopus.util;

import org.jsoup.Jsoup;
import org.lvgo.octopus.core.Duplicate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据连接
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/6 11:43
 */
public class HttpConnection {

    /**
     * 源地址
     */
    private String url;
    /**
     * 请求头
     */
    private Map<String, Object> header = new HashMap<>();
    /**
     * 是否开启网页地址全递归
     */
    private boolean recursion;

    private HttpConnection(String url) {
        this.url = url;
    }

    public HttpConnection() {
    }

    /**
     * 通过构建者模式来创建网络连接
     *
     * @param url 需要连接的目标网络地址
     * @return 数据连接对象
     */
    public static HttpConnection building(String url) {
        return new HttpConnection(url);
    }

    /**
     * 增加请求所需请求头
     *
     * @param key   name
     * @param value value
     * @return
     */
    public HttpConnection header(String key, String value) {
        this.header.put(key, value);
        return this;
    }


    /**
     * 构建者后调用,参数组装完成, 建立连接, 获取内容
     *
     * @return 返回网络内容
     */
    public Duplicate connect() {

        // 通过http请求拿到返回页面

        Duplicate duplicate = new Duplicate();
        try {
            duplicate.setDocument(Jsoup.connect(url).get());
        } catch (IOException e) {
            e.printStackTrace();
            duplicate.setSuccess(false);
        }
        return duplicate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpConnection{");
        sb.append("url='").append(url).append('\'');
        sb.append(", header=").append(header);
        sb.append('}');
        return sb.toString();
    }
}
