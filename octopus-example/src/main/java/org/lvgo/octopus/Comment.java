package org.lvgo.octopus;


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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 影片id
     */
    private String movieId;

    /**
     * 评论人
     */
    private String commentPeople;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论日期
     */
    private String commentDate;

    /**
     * 评论星级
     */
    private String commentRating;

    /**
     * 评论来源
     */
    private String source;

    /**
     * 数据时间
     */
    private LocalDateTime dataTime;

    /**
     * 点击有用数
     */
    private Integer valuable;

    /**
     * 点击没用数
     */
    private Integer worthless;


}
