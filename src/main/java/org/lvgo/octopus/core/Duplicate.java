package org.lvgo.octopus.core;

import org.jsoup.nodes.Document;

/**
 * 捕捉回的数据
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/11/4 13:50
 */
public class Duplicate extends Data {


    /**
     * 请求是否成
     */
    private boolean success;

    /**
     * URL
     */
    private String baseUrl;

    /**
     * 文档数据
     */
    private Document document;

    public Duplicate() {
    }

    public Duplicate(boolean success, String baseUrl, Document document) {
        this.success = success;
        this.baseUrl = baseUrl;
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
