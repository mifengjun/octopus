package org.lvgo.octopus;

import org.jsoup.nodes.Document;

/**
 * 返回载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 16:16
 */
public class Response {

    private String content;

    /**
     * jsoup 的返回对象
     */
    private Document document;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
