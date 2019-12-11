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
    /**
     * 请求头
     */
    private Map<String, String> headers;

    private Octopus() {
        this.success = true;
        this.headers = new HashMap<>(5);
    }

    public static Octopus init() {
        return new Octopus();
    }

    public Octopus url(String url) {
        this.url = url;
        return this;
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
    private void connect(boolean get) {

        try {
            Connection connect;
            connect = Jsoup.connect(this.url);
            if (!headers.isEmpty()) {
                connect.headers(headers);
            }
            if (get) {
                this.document = connect.get();
            } else {
                this.document = connect.post();

            }
        } catch (IOException e) {
            e.printStackTrace();
            this.success = false;
        }
    }

    public Octopus get() {
        connect(true);
        return this;
    }

    public Octopus post() {
        connect(false);
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
