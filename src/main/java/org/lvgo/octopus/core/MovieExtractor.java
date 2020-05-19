package org.lvgo.octopus.core;

/**
 * 电影提取器
 *
 * @author lvgorice@gmail.com
 * @date 2020/5/19 22:54
 * @since 1.0.0
 */
public interface MovieExtractor extends Extractor {

    /**
     * 抓取电影信息
     *
     * @param octopus 八爪鱼
     */
    void fetchMovieInfo(Octopus octopus);
}
