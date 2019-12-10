package org.lvgo.octopus.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 网页复制器
 * <p>
 * 提供网络地址及对应要求数据, 填入复制器, 对目标数据进行复制, 返回网页副本
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 10:41
 */
public class Duplicator {
    /**
     * 源地址
     */
    private String url;

    /**
     * 网页副本, 用于返回 , 在创建复制器初始化参数时设置
     */
    private Duplicate duplicate;

    private Map<String, String> headers = new HashMap<>(5);

    public Duplicator() {
    }

    private Duplicator(String url) {
        this.url = url;
        duplicate = new Duplicate(true, url, null);
    }

    public static Duplicator url(String url) {
        return new Duplicator(url);
    }

    public Duplicator header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Duplicator headers(Map<String, String> map) {
        headers.putAll(map);
        return this;
    }

    /**
     * 复制
     */
    public Duplicate copy() {

        try {
            Connection connect = Jsoup.connect(this.url);
            if (!headers.isEmpty()) {
                connect.headers(headers);
            }
            duplicate.setDocument(connect.get());
        } catch (IOException e) {
            e.printStackTrace();
            duplicate.setSuccess(false);
        }

        return duplicate;
    }

    public String getUrl() {
        return url;
    }
}
