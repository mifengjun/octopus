package org.lvgo.weboctopus.movie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.weboctopus.common.GeneralConstant;
import org.lvgo.weboctopus.extractor.DouBanExtractor;
import org.lvgo.weboctopus.movie.bean.Movie;
import org.lvgo.weboctopus.movie.mapper.MovieMapper;
import org.lvgo.weboctopus.movie.service.IMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 电影信息表 服务实现类
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-14
 */
@Service
@Slf4j
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Resource
    private DouBanExtractor douBanExtractor;

    @Override
    public void fetchData(String source) {
        if (GeneralConstant.SOURCE_DOU_BAN.equals(source)) {
            douBanMovie();
        } else {
            log.info("啥也没有");
        }
    }

    private void douBanMovie() {
        String movieId = "30176393";
        String movieUrl = "https://movie.douban.com/subject/" + movieId;
        // 定义一个八爪鱼
        Octopus octopus = Octopus.init().param("movieId", movieId);
        // 抓取一次电影信息
        douBanExtractor.fetchMovieInfo(octopus.connect(movieUrl));

        String commentUrl = "https://movie.douban.com/subject/" + movieId + "/reviews";
        octopus.url(commentUrl).extractor(douBanExtractor).pageDown(true).page(3).pageSize(20);

        octopus.start();
    }
}
