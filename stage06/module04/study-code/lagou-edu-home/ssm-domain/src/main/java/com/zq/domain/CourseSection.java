package com.zq.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.List;

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
public class CourseSection implements Serializable {
    private static final long serialVersionUID =  5359608904496566723L;

    /**
     * id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 课程id
     */
    @Setter
    @Getter
    private Integer courseId;

    /**
     * 章节名
     */
    @Setter
    @Getter
    private String sectionName;

    /**
     * 章节描述
     */
    @Setter
    @Getter
    private String description;

    /**
     * 记录创建时间
     */
    @Setter
    @Getter
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Setter
    @Getter
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Setter
    @Getter
    private Integer isDe;

    /**
     * 排序字段
     */
    @Setter
    @Getter
    private Integer orderNum;

    /**
     * 状态，0:隐藏；1：待更新；2：已发布
     */
    @Setter
    @Getter
    private Integer status;

    /**
     * 1对∞的数据库表关系中, 在 1 的一方的实体类里, 会封装一个 ∞ 的对象的集合
     * 1的类型是{@link CourseSection},
     * ∞的类型是{@link CourseLesson}
     */
    @Getter
    @Setter
    private List<CourseLesson> lessonList;

}
