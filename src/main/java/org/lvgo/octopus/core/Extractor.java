package org.lvgo.octopus.core;

/**
 * 数据提取器
 *
 * @author lvgorice@gmail.com
 * @version 1.0
 * @date 2019/12/10 11:40
 */
@FunctionalInterface
public interface Extractor {

    /**
     * 从副本中提取你要的数据
     *
     * @return 可利用数据
     */
    Data extract(Octopus octopus);
}
