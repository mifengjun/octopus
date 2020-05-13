package org.lvgo.weboctopus.doubanmovie.controller;


import org.lvgo.weboctopus.doubanmovie.service.IMovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-13
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private IMovieService movieService;

    @RequestMapping("/fetch")
    public String fetchData(String id) {
        movieService.fetchData();
        return id + " - ok!";
    }
}
