package org.lvgo.weboctopus.movie.service.impl;

import org.junit.jupiter.api.Test;
import org.lvgo.weboctopus.WebOctopusApplicationTests;
import org.lvgo.weboctopus.movie.service.IMovieService;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * //TODO 一句话描述此类的作用
 *
 * @author lvgorice@gmail.com
 * @date 2020/5/19 23:17
 * @since 1.0.0
 */
class MovieServiceImplTest extends WebOctopusApplicationTests {

    @Resource
    private IMovieService movieService;

    @Test
    void fetchData() {
        movieService.fetchData("douban");
    }
}