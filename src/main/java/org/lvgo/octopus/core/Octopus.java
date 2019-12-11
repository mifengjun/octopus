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
     * 爬取数据地址
     */
    private String url;
    /**
     * 请求是否成功
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

    /**
     * 是否为 get 请求, 否则为 false
     */
    private boolean get;

    /**
     * 提取
     */
    private Extractor extractor;
    /**
     * 提取的数据
     */
    private Data data;
    /**
     * 翻页抓取
     */
    private boolean pageDown;
    private int page;
    private int pageSize;
    private Octopus() {
        this.success = true;
        this.headers = new HashMap<>(5);
    }

    public static Octopus init() {
        return new Octopus();
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isPageDown() {
        return pageDown;
    }

    public void setPageDown(boolean pageDown) {
        this.pageDown = pageDown;
    }

    public Octopus url(String url) {
        this.url = url;
        return this;
    }

    public Octopus pageDown(boolean pageDown) {
        this.pageDown = pageDown;
        return this;
    }

    public Octopus page(int page) {
        this.page = page;
        return this;
    }

    public Octopus pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * 请求头, 提供单个请求头设置, key value 格式
     */
    public Octopus header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * 请求头, 提供多个请求头参数设置, 键值对集合设置
     */
    public Octopus headers(Map<String, String> map) {
        headers.putAll(map);
        return this;
    }

    /**
     * 连接获取页面数据
     */
    public Octopus connect(String url) {
        try {
            Connection connect;
            connect = Jsoup.connect(url == null ? this.url : url);
            if (!headers.isEmpty()) {
                connect.headers(headers);
            }
            if (get) {
                this.document = connect.get();
            } else {
                this.document = connect.post();

            }
            this.success = true;
        } catch (IOException e) {
            e.printStackTrace();
            this.success = false;
            this.document = null;
        }
        return this;
    }

    public Octopus get() {
        this.get = true;
        return this;
    }

    public Octopus post() {
        this.get = false;
        return this;
    }

    /**
     * 解析数据
     *
     * @param extractor 解析器
     */
    public Octopus extractor(Extractor extractor) {
        this.extractor = extractor;
        return this;
    }

    /**
     * 章鱼开始甩腿
     *
     * @return 解析结果数据
     */
    public Data start() {
        // 建立连接
        connect(null);
        // 解析数据
        return extractor.extract(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
