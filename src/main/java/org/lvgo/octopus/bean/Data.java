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
public class Data extends BaseBean {

    private String tableName;
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
