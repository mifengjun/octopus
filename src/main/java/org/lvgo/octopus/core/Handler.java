package org.lvgo.octopus.core;

import org.lvgo.octopus.assist.Data;

/**
 * 处理器
 * <p>
 * 对数据结果的处理
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2020/5/28 15:28
 */
public interface Handler {

    /**
     * 处理解析出来的数据
     *
     * @param data 解析出来的数据对象
     */
    void handler(Data data);
}
