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
public class CourseLesson implements Serializable {

    private static final long serialVersionUID =  3688361798900840522L;

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
     * 章节id
     */
    @Setter
    @Getter
    private Integer sectionId;

    /**
     * 课时主题
     */
    @Setter
    @Getter
    private String theme;

    /**
     * 课时时长(分钟)
     */
    @Setter
    @Getter
    private Integer duration;

    /**
     * 是否免费
     */
    @Setter
    @Getter
    private Integer isFree;

    /**
     * 记录创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter
    @Getter
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter
    @Getter
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Setter
    @Getter
    private Integer isDel;

    /**
     * 排序字段
     */
    @Setter
    @Getter
    private Integer orderNum;

    /**
     * 课时状态,0-隐藏，1-未发布，2-已发布
     */
    @Setter
    @Getter
    private Integer status;
    
    /**
     * ∞对1的数据库表关系中, 在 ∞ 的一方的实体类里, 会封装一个 1 的对象
     * ∞的类型是{@link CourseLesson},
     * 1的类型是{@link CourseSection}
     */
    @Getter
    @Setter
    private CourseSection courseSection;

}
