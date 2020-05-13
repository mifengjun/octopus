package org.lvgo.weboctopus.doubanmovie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.lvgo.weboctopus.doubanmovie.bean.Movie;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-13
 */
public interface IMovieService extends IService<Movie> {

    void fetchData();
}
