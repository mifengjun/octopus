package org.lvgo.octopus.core;

import org.lvgo.octopus.assist.OctopusData;

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
     * @param octopusData 解析出来的数据对象
     */
    void handler(OctopusData octopusData);
}
