package org.lvgo.weboctopus.doubanmovie.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lvgorice@gmail.com
 * @since 2020-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 影片id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 影片名称
     */
    private String movieName;

    /**
     * 影片年份
     */
    private String initialReleaseDate;

    /**
     * 影片时长
     */
    private Integer movieTime;

    /**
     * 制片国家/地区
     */
    private String producerCountry;

    /**
     * 影片来源
     */
    private String source;

    /**
     * 影片来源
     */
    private String sourceName;

    /**
     * 数据时间
     */
    private LocalDateTime dataTime;


}
