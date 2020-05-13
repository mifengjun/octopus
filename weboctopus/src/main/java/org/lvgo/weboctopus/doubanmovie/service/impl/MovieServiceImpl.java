package org.lvgo.weboctopus.doubanmovie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.lvgo.example.DoubanExtractor;
import org.lvgo.octopus.bean.OctopusData;
import org.lvgo.octopus.core.Octopus;
import org.lvgo.weboctopus.doubanmovie.bean.Comment;
import org.lvgo.weboctopus.doubanmovie.bean.Movie;
import org.lvgo.weboctopus.doubanmovie.mapper.CommentMapper;
import org.lvgo.weboctopus.doubanmovie.mapper.MovieMapper;
import org.lvgo.weboctopus.doubanmovie.service.IMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-13
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

    @Resource
    private MovieMapper movieMapper;
    @Resource
    private CommentMapper commentMapper;

    @Override
    public void fetchData() {
        // 少年的你影评
//        String movieId = "30166972";
        // 机器人总动员
//        String movieId = "2131459";
        // 爱，死亡和机器人 第一季 Love, Death & Robots Season 1 (2019)
//        String movieId = "30424374";
        // 误杀
        String movieId = "30176393";


        String url = "https://movie.douban.com/subject/" + movieId + "/reviews";

        Octopus octopus = Octopus.init();

        octopus.url(url).extractor(new DoubanExtractor()).pageSize(20)
                .start();

        OctopusData octopusData = octopus.getOctopusData();
        List<Map<String, Object>> dataList = octopusData.getDataList();

        for (Map<String, Object> map : dataList) {
            Comment comment = new Comment();
            comment.setMovieId(30176393L);
            comment.setSource("douban");
            comment.setComment(map.get("comment").toString());
            comment.setCommentRating(map.get("star").toString());
            comment.setValuable(map.get("valuable").toString());
            comment.setWorthless(map.get("worthless").toString());
            comment.setDataTime(LocalDateTime.now());
            commentMapper.insert(comment);
        }
    }
}
