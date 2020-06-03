package org.lvgo.octopus;

import java.io.Serializable;

/**
 * 数据载体
 * <p>
 * 从解析器中处理后的数据载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 13:35
 */
public class Data implements Serializable {
    private static final long serialVersionUID = 5191403235109447830L;

    private int type;
    private String value;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
