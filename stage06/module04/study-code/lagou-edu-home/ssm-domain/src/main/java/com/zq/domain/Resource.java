package com.zq.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * 资源表<code>resource</code>的实体类
 * 
 * @author zq007
 * @version V1.0
 * @date 2022-06-10 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "handler")
public class Resource implements Serializable {

    private static final long serialVersionUID =  6651970322596116644L;

    /**
     * 资源id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 资源名称
     */
    @Setter
    @Getter
    private String name;

    /**
     * 资源url
     */
    @Setter
    @Getter
    private String url;

    /**
     * 分类id
     */
    @Setter
    @Getter
    private Integer categoryId;

    /**
     * 简介
     */
    @Setter
    @Getter
    private String description;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime updatedTime;

    /**
     * 创建人
     */
    @Setter
    @Getter
    private String createdBy;

    /**
     * 更新人
     */
    @Setter
    @Getter
    private String updatedBy;

}
