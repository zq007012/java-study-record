package com.zq.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-05-21 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseMedia implements Serializable {

    private static final long serialVersionUID =  6716546859451504700L;

    /**
     * 课程媒体主键ID
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 课程Id
     */
    @Setter
    @Getter
    private Integer courseId;

    /**
     * 章ID
     */
    @Setter
    @Getter
    private Integer sectionId;

    /**
     * 课时ID
     */
    @Setter
    @Getter
    private Integer lessonId;

    /**
     * 封面图URL
     */
    @Setter
    @Getter
    private String coverImageUrl;

    /**
     * 时长（06:02）
     */
    @Setter
    @Getter
    private String duration;

    /**
     * 媒体资源文件对应的EDK
     */
    @Setter
    @Getter
    private String fileEdk;

    /**
     * 文件大小MB
     */
    @Setter
    @Getter
    private Double fileSize;

    /**
     * 文件名称
     */
    @Setter
    @Getter
    private String fileName;

    /**
     * 媒体资源文件对应的DK
     */
    @Setter
    @Getter
    private String fileDk;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter
    @Getter
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Setter
    @Getter
    private LocalDateTime updateTime;

    /**
     * 是否删除，0未删除，1删除
     */
    @Setter
    @Getter
    private Integer isDel;

    /**
     * 时长，秒数（主要用于音频在H5控件中使用）
     */
    @Setter
    @Getter
    private Integer durationNum;

    /**
     * 媒体资源文件ID
     */
    @Setter
    @Getter
    private String fileId;

}
