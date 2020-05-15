package org.lvgo.octopus.core;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.lvgo.octopus.bean.AbstractOctopusBean;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.octopus.bean.OctopusProxy;
import org.lvgo.silent.TaskHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 八爪魚抓取 , 八爪鱼爪子比较多, 而且灵活.
 * <p>
 * 所以我们的爬虫也支持多节点, 多线程, 高容错, 高性能呢.
 * <p>
 * 提供网络地址及对应要求数据, 填入复制器, 对目标数据进行复制, 返回网页副本
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 10:41
 */
public class Octopus extends AbstractOctopusBean {
    private static final long serialVersionUID = -6080428937930565035L;
    /**
     * 最大尝试次数
     */
    private static final int MAX_ATTEMPTS = 3;
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
    private ThreadLocal<Document> document;
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
    /**
     * 请求超时时间
     */
    private int timeoutMilliseconds = 30 * 1000;
    /**
     * 章鱼代理
     */
    private OctopusProxy octopusProxy;

    /**
     * 当前线程尝试次数
     */
    private ThreadLocal<Integer> attemptCount = new ThreadLocal<>();

    private Octopus() {
        this.success = true;
        this.headers = new HashMap<>(5);
        this.document = new ThreadLocal<>();
    }

    /**
     * 初始化八爪鱼
     * <p>
     * //TODO:在这里可以做每个八爪鱼的数据统一监听, 留好口子, 已被后续使用
     *
     * @return 鲜活的八爪鱼
     */
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
        Document document = this.document.get();
        this.document.remove();
        return document;
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
     * 设置超时时间, 默认30秒
     *
     * @param millis 毫秒值
     * @return 返回一条大章鱼
     */
    public Octopus timeOut(int millis) {
        Validate.isTrue(millis >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
        this.timeoutMilliseconds = millis;
        return this;
    }

    public Octopus proxy(IpProxy ipProxy) {
        octopusProxy = OctopusProxy.getInstance(ipProxy);
        return this;
    }


    /**
     * 连接获取页面数据, 通过控制url参数非空来决定数据地址
     * <p>
     * 统一入口, 所有请求连接通过此方法连接
     */
    public Octopus connect(String url) {
        Integer value = attemptCount.get() == null ? 1 : attemptCount.get();

        String realUrl = url == null ? this.url : url;
        // org.jsoup.Connection
        Connection connect = Jsoup.connect(realUrl);

        // 获取随机代理
        OctopusProxy randomProxy = null;

        if (this.octopusProxy != null) {
            randomProxy = this.octopusProxy.randomProxy();
            if (this.octopusProxy.isEmpty()) {
                log.error("无可用代理IP");
                this.success = false;
                this.document = null;
                return this;
            }
            // 代理IP
            connect.proxy(randomProxy.getHost(), randomProxy.getPort());
            log.info("使用代理ip:{},请求地址:{}", randomProxy, realUrl);
        }

        if (!headers.isEmpty()) {
            // 请求头
            connect.headers(headers);
        }

        // 请求超时时间
        connect.timeout(timeoutMilliseconds);

        // 发送请求
        try {
            if (get) {
                this.document.set(connect.get());
            } else {
                this.document.set(connect.post());
            }
            this.success = true;
        } catch (IOException e) {

            this.success = false;
            document.remove();

            log.error("请求失败: {}", e.getMessage());
            if (randomProxy != null) {
                this.octopusProxy.remove(randomProxy);
            }
            if (value <= MAX_ATTEMPTS) {
                log.error("尝试第{}次请求....", value);
                attemptCount.set(value + 1);
                connect(url);
            } else {
                log.error("尝试{}次请求失败 .. 放弃处理", MAX_ATTEMPTS);
            }
        }
        attemptCount.remove();
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
     * 设置线程数
     *
     * @param count 线程数
     * @return 大八爪鱼.... 咕噜咕噜..崩
     */
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
        log.info("=================数据抓取参数===============");
        log.info("目标地址 :" + this.url);
        log.info("请求方式 :" + (this.get ? "GET" : "POST"));
        log.info("提取器 :" + this.extractor.getClass().getSimpleName());
        log.info("翻页 :" + (this.pageDown ? "是" : "否"));
        if (this.pageDown) {
            log.info("分页数 :" + this.page);
            log.info("每页大小 :" + this.pageSize);
        }
        log.info("多线程 :" + (this.concurrent ? "是" : "否"));
        log.info("线程数 :" + this.threadSize);
        log.info("============================================");


        // 连接获取上下文信息
        connect(null);

        if (!this.success) {
            log.error("未获取到任何内容信息, 请分析!!");
            return;
        }

        // 通过是否翻页区分是否使用多线程 , 如果不分页或者只查询一页的时候, 使用单线程,
        if (!pageDown || page == 1) {
            // 解析数据
            extractor.extract(this);
        } else {
            // 开启多线程处理分页数据
            OctopusPage octopusPage = extractor.getPageInfo(this);
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
    }


    /**
     * 啊? 这还要注释 , 返回线程数
     *
     * @return >-> 线程数
     */
    public int getThreadSize() {
        return threadSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
