package org.lvgo.octopus;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

/**
 * 返回载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 16:16
 */
public class Response {

    /**
     * jsoup 的返回的dom对象
     */
    private Document document;
    /**
     * 请求是否成功
     */
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 探测深度和url
     */
    private Map<String, List<String>> probeUrl;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
