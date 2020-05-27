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
 * 评论表
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.INPUT)
    private String commentId;

    /**
     * 影片id
     */
    @TableField("movie_id")
    private String movieId;

    /**
     * 评论人
     */
    @TableField("comment_people")
    private String commentPeople;

    /**
     * 评论内容
     */
    @TableField("comment")
    private String comment;

    /**
     * 评论日期
     */
    @TableField("comment_date")
    private String commentDate;

    /**
     * 评论星级
     */
    @TableField("comment_rating")
    private String commentRating;

    /**
     * 评论来源
     */
    @TableField("source")
    private String source;

    /**
     * 数据时间
     */
    @TableField("data_time")
    private LocalDateTime dataTime;

    /**
     * 点击有用数
     */
    @TableField("valuable")
    private Integer valuable;

    /**
     * 点击没用数
     */
    @TableField("worthless")
    private Integer worthless;


}
