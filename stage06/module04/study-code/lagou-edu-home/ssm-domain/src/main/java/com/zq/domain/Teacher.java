package com.zq.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022-05-27
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Teacher implements Serializable {

    private static final long serialVersionUID = 4695599715012422131L;

    /**
     * id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 课程ID
     */
    @Setter
    @Getter
    private Integer courseId;

    /**
     * 讲师姓名
     */
    @Setter
    @Getter
    private String teacherName;

    /**
     * 职务
     */
    @Setter
    @Getter
    private String position;

    /**
     * 讲师介绍
     */
    @Setter
    @Getter
    private String description;

    /**
     * 记录创建时间
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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

}
