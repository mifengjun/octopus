package org.lvgo.octopus.core;

/**
 * 数据提取
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 11:40
 */
@FunctionalInterface
public interface Extractor {

    /**
     * 提取数据
     */
    Data extract(Octopus octopus);
}
