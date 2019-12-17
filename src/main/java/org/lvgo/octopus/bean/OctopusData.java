package org.lvgo.octopus.bean;

import java.util.List;
import java.util.Map;

/**
 * 数据
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/11/4 13:47
 */
public class OctopusData extends OctopusBeans {

    private static final long serialVersionUID = -7446757364517305651L;
    /**
     * 数据标题
     */
    private String tableName;

    /**
     * 数据项
     */
    private List<Map<String, Object>> dataList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Map<String, Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, Object>> dataList) {
        this.dataList = dataList;
    }

}
