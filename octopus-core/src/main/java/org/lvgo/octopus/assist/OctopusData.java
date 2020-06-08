package org.lvgo.octopus.assist;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 数据载体
 * <p>
 * 从解析器中处理后的数据载体
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 13:35
 */
public class OctopusData extends HashMap implements Serializable {
    private static final long serialVersionUID = 5191403235109447830L;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
