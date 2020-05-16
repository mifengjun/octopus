package org.lvgo.octopus.core;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.lvgo.octopus.bean.OctopusPage;
import org.lvgo.silent.TaskHandler;

/**
 * 数据提取
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/12 10:21
 */
public interface Extractor {

    /**
     * 多线程解析时, 获取记录总页数&分页数据地址
     *
     * @param octopus 连接器(复制机)
     * @return 记录总页数
     */
    OctopusPage getPageInfo(Octopus octopus);

    /**
     * 提取数据
     *
     * @param octopus 连接信息, 包含请求及返回数据内容
     */
    void extract(Octopus octopus);


    /**
     * 并发解析数据, 提供这个方法出于对 silent 不熟悉仍然可以开箱即用的多线程
     * <p>
     * 此方法为对 silent 处理数据的封装, 将上下文参数及数据参数 elements 提供给 #concurrentHandle 来处理
     *
     * @param octopus  上下文参数
     * @param elements 数据元素
     */
    default void concurrentHandle(Octopus octopus, Elements elements) {
        // 线程数大于0时, 启动多线程处理
        new TaskHandler<Element>(elements) {
            @Override
            public void run(Element element) {
                // 提供方法用于用户对数据的解析处理
                elementHandle(octopus, element);
            }
        }.sync(true).execute(Math.max(octopus.getPageSize(), 1));
    }

    /**
     * 多线程数据数据元素处理
     *
     * @param octopus 上下文
     * @param element 当前数据
     */
    void elementHandle(Octopus octopus, Element element);

}
