package com.zq.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * menu表的实体类
 *  * @author zq007
 * @version V1.0
 * @date 2022-06-06 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "handler")
public class Menu implements Serializable {

    private static final long serialVersionUID =  1452044126750884154L;

    /**
     * id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 父菜单id
     */
    @Setter
    @Getter
    private Integer parentId;

    /**
     * 菜单路径
     */
    @Setter
    @Getter
    private String href;

    /**
     * 菜单图标
     */
    @Setter
    @Getter
    private String icon;

    /**
     * 菜单名称
     */
    @Setter
    @Getter
    private String name;

    /**
     * 描述
     */
    @Setter
    @Getter
    private String description;

    /**
     * 排序号
     */
    @Setter
    @Getter
    private Integer orderNum;

    /**
     * 是否展示
     */
    @Setter
    @Getter
    private Integer shown;

    /**
     * 菜单层级，从0开始
     */
    @Setter
    @Getter
    private Integer level;

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

    /**
     * 本菜单的子菜单集合
     */
    @Getter
    @Setter
    private List<Menu> subMenus;

}
