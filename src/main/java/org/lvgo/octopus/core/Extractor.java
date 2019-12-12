package org.lvgo.octopus.core;

/**
 * 数据提取
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 10:21
 */
public abstract class Extractor {


    /**
     * 多线程解析时, 获取记录总页数
     *
     * @param octopus 连接器(复制机)
     * @return 记录总页数
     */
    public abstract int getPage(Octopus octopus);

    /**
     * 提取数据
     *
     * @param octopus 连接信息, 包含请求及返回数据内容
     */
    public abstract void extract(Octopus octopus);
}
