package org.lvgo.weboctopus.movie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 电影信息表
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片id
     */
    @TableId(value = "movie_id", type = IdType.INPUT)
    private String movieId;

    /**
     * 影片名称
     */
    @TableField("movie_name")
    private String movieName;

    /**
     * 影片年份
     */
    @TableField("initial_release_date")
    private String initialReleaseDate;

    /**
     * 影片时长
     */
    @TableField("movie_time")
    private Integer movieTime;

    /**
     * 制片国家/地区
     */
    @TableField("producer_country")
    private String producerCountry;

    /**
     * 影片来源
     */
    @TableField("source")
    private String source;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private LocalDateTime dataTime;


}
