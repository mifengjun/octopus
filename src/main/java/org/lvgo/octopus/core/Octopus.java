package org.lvgo.octopus.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.silent.TaskHandler;

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
     * 爬取数据地址, 外部提供给抓取器的地址
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
    private boolean get = true;

    /**
     * 数据提取接口
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
    /**
     * 页码
     */
    private int page;
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 线程数
     */
    private int threadSize;
    /**
     * 是否开启多线程
     */
    private boolean concurrent;

    private Octopus() {
        this.success = true;
        this.headers = new HashMap<>(5);
    }

    public static Octopus init() {
        return new Octopus();
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public boolean isConcurrent() {
        return concurrent;
    }

    public void setConcurrent(boolean concurrent) {
        this.concurrent = concurrent;
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
     * 连接获取页面数据, 通过控制url参数非空来决定数据地址
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

    public Octopus threads(int count) {
        this.threadSize = count;
        this.concurrent = count > 1;
        return this;
    }

    /**
     * 章鱼开始甩腿 , 解析数据
     */
    public void start() {


        // 打印此次抓取数据的基本信息
        System.out.println("=================数据抓取参数===============");
        System.out.println("目标地址 :" + this.url);
        System.out.println("请求方式 :" + (this.get ? "GET" : "POST"));
        System.out.println("提取器 :" + this.extractor.getClass().getSimpleName());
        System.out.println("分页 :" + (this.pageDown ? "是" : "否"));
        System.out.println("分页数 :" + this.page);
        System.out.println("每页大小 :" + this.pageSize);
        System.out.println("多线程 :" + (this.concurrent ? "是" : "否"));
        System.out.println("线程数 :" + this.threadSize);
        System.out.println("============================================");


        // 连接获取上下文信息
        connect(null);
        // 获取总页数
        // 是否翻页, 默认不翻页
        if (!pageDown) {
            // 解析数据
            extractor.extract(Octopus.this);
            return;
        }

        OctopusPage octopusPage = extractor.getPageInfo(this);

        // 线程数大于 0 时, 启动多线程处理
        new TaskHandler<String>(octopusPage.getUrls()) {
            @Override
            public void run(String url) {
                // 建立连接
                connect(url);
                // 解析数据
                extractor.extract(Octopus.this);
            }
        }.sync(true).execute(threadSize > 1 ? threadSize : 1);
    }


    public int getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(int threadSize) {
        this.threadSize = threadSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
