package org.lvgo.weboctopus.movie.controller;


import org.lvgo.weboctopus.movie.service.IMovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 电影信息表 前端控制器
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private IMovieService movieService;

    @RequestMapping("/fetch")
    public String fetchData(String source) {
        movieService.fetchData(source);
        return source + " - OK!";
    }
}
