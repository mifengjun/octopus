package org.lvgo.octopus.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 八爪魚抓取
 * <p>
 * 提供网络地址及对应要求数据, 填入复制器, 对目标数据进行复制, 返回网页副本
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 10:41
 */
public class Octopus {
    /**
     * 源地址
     */
    private String url;
    /**
     * 请求是否成
     */
    private boolean success;
    /**
     * 文档数据
     */
    private Document document;
    private Map<String, String> headers;

    public Octopus() {
    }

    private Octopus(String url) {
        this.url = url;
        this.success = true;
        this.headers = new HashMap<>(5);
    }

    public static Octopus url(String url) {
        return new Octopus(url);
    }

    public boolean isSuccess() {
        return success;
    }

    public Document getDocument() {
        return document;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Octopus header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Octopus headers(Map<String, String> map) {
        headers.putAll(map);
        return this;
    }

    /**
     * 复制
     */
    public Octopus copy() {

        try {
            Connection connect = Jsoup.connect(this.url);
            if (!headers.isEmpty()) {
                connect.headers(headers);
            }
            this.document = connect.get();
        } catch (IOException e) {
            e.printStackTrace();
            this.success = false;
        }
        return this;
    }

    public Data extract(Extractor extractor) {
        return extractor.extract(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
