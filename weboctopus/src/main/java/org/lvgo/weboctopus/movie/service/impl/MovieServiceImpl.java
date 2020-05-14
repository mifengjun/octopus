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

    @Override
    public void fetchData(String source) {
        if (GeneralConstant.SOURCE_DOU_BAN.equals(source)) {

            String movieId = "30176393";

            String url = "https://movie.douban.com/subject/" + movieId + "/reviews";

            Octopus octopus = Octopus.init();

            octopus.url(url).extractor(new DouBanExtractor()).pageSize(20);

            octopus.start();

            log.info(octopus.getDataList().toString());


        } else {
            log.info("啥也没有");
        }
    }
}
