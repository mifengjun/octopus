package org.lvgo.octopus.douban;


import org.lvgo.octopus.assist.OctopusData;

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
public class Comment extends OctopusData implements Serializable {

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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCommentPeople() {
        return commentPeople;
    }

    public void setCommentPeople(String commentPeople) {
        this.commentPeople = commentPeople;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(String commentRating) {
        this.commentRating = commentRating;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public void setDataTime(LocalDateTime dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getValuable() {
        return valuable;
    }

    public void setValuable(Integer valuable) {
        this.valuable = valuable;
    }

    public Integer getWorthless() {
        return worthless;
    }

    public void setWorthless(Integer worthless) {
        this.worthless = worthless;
    }
}
