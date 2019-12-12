package org.lvgo.octopus.bean;

import java.util.List;

/**
 * 分页信息
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 11:41
 */
public class OctopusPage extends BaseBean {

    /**
     * 总页数
     */
    private int pageTotal;

    /**
     * 数据地址
     */
    private List<String> urls;

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
