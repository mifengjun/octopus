package org.lvgo.octopus;

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
    /**
     * 请求载体
     */
    protected Request request;
    /**
     * 返回载体
     */
    protected Response response;
    /**
     * 请求状态
     */
    protected String statusCode;
    /**
     * 请求是否成功
     */
    protected boolean success;
    protected String url;
    /**
     * 请求超时时间
     */
    private int timeoutMilliseconds = 30 * 1000;
    /**
     * 是否为 get 请求, 否则为 false
     */
    private boolean get = true;


    /**
     * 网页下载, 需自主实现
     *
     * @return 网页内容
     */
    public Response downLoad() {
        return this.response;
    }

    public Simulator request(Request request) {
        this.request = request;
        return this;
    }


    public Simulator url(String url) {
        this.url = url;
        return this;
    }

    public int getTimeoutMilliseconds() {
        return timeoutMilliseconds;
    }

    public void setTimeoutMilliseconds(int timeoutMilliseconds) {
        this.timeoutMilliseconds = timeoutMilliseconds;
    }

    public boolean isGet() {
        return get;
    }

    public void setGet(boolean get) {
        this.get = get;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
