package org.lvgo.weboctopus.movie.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 影片id
     */
    private Long movieId;

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
    private LocalDateTime commentDate;

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
