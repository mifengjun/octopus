package org.lvgo.octopus;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.lvgo.octopus.util.OctopusUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器模拟器
 * <p>
 * 通过这个浏览器模拟器来进行看似与浏览器行为一致的操作, 包括相关的输入与输出
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 14:07
 */
public class Simulator {

    private Logger logger = LoggerFactory.getLogger(Simulator.class);

    /**
     * 返回载体
     */
    private Response response = new Response();
    /**
     * 请求状态
     */
    private String statusCode;
    /**
     * 请求是否成功
     */
    private boolean success;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求超时时间
     */
    private int timeoutMilliseconds = 30 * 1000;
    /**
     * 请求间隔时间  单位:秒
     */
    private int interval = 3;
    /**
     * 忽略内容类型
     */
    private boolean ignoreContentType;

    /**
     * #parser 解析后的数据
     */
    private Data data;
    /**
     * 请求头
     */
    private Map<String, String> headers = new HashMap<>(3);

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Simulator() {
    }


    /**
     * 请求头, 提供单个请求头设置, key value 格式
     */
    public Simulator header(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * 设置间隔时间, 单位:秒
     * <p>
     * 如果小于等于0 或 不设置则默认3秒间隔
     *
     * @param interval 间隔时间
     * @return 模拟器
     */
    public Simulator interval(int interval) {
        if (interval <= 0) {
            interval = 3;
        }
        this.interval = interval;
        return this;
    }

    /**
     * 超时时间设置, 单位: 秒
     *
     * @param seconds 超时秒数
     * @return 模拟器
     */
    public Simulator timeout(int seconds) {
        if (seconds < 0) {
            logger.warn("second less then zero, set default value");
            return this;
        }
        this.timeoutMilliseconds = seconds * 1000;
        return this;
    }

    /**
     * 网页下载, 可自定义实现
     */
    public void downLoad(String url) throws IOException {
        logger.info("开始 downLoad ... 目标地址:{}", url);

        // 建立连接, 并且设置是否忽略文本类型
        Connection connect = Jsoup.connect(url).ignoreContentType(ignoreContentType);

        if (headers.size() != 0) {
            connect.headers(headers);
        }

        // 请求超时时间
        connect.timeout(timeoutMilliseconds);

        try {
            // 通过手动线程阻塞来控制时间间隔
            OctopusUtils.sleep(interval);

            response.setDocument(connect.get());


        } catch (IOException e) {
            logger.error("{} 数据请求失败", url);
            throw e;
        }
        logger.info("完成 downLoad ... 目标地址:{}", url);
    }

    public Simulator ignoreContentType(boolean ignoreContentType) {
        this.ignoreContentType = ignoreContentType;
        return this;
    }


    public int getTimeoutMilliseconds() {
        return timeoutMilliseconds;
    }


    public Response getResponse() {
        return response;
    }


    public String getStatusCode() {
        return statusCode;
    }


    public boolean isSuccess() {
        return success;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
