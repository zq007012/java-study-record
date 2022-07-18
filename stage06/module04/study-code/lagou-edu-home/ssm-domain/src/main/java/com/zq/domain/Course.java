package com.zq.domain;

import com.fasterxml.jackson.annotation.*;
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
 * @date 2022-05-21
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Course implements Serializable {

    private static final long serialVersionUID = 702910674163727716L;

    /**
     * id
     */
    @Setter
    @Getter
    @JsonIgnoreProperties
    private Integer id;

    /**
     * 课程名
     */
    @Setter
    @Getter
    private String courseName;

    /**
     * 课程一句话简介
     */
    @Setter
    @Getter
    private String brief;

    /**
     * 原价
     */
    @Setter
    @Getter
    private Double price;

    /**
     * 原价标签
     */
    @Setter
    @Getter
    private String priceTag;

    /**
     * 优惠价
     */
    @Setter
    @Getter
    private Double discounts;

    /**
     * 优惠标签
     */
    @Setter
    @Getter
    private String discountsTag;

    /**
     * 描述markdown
     */
    @Setter
    @Getter
    private String courseDescriptionMarkDown;

    /**
     * 课程描述
     */
    @Setter
    @Getter
    private String courseDescription;

    /**
     * 课程分享图片url
     */
    @Setter
    @Getter
    private String courseImgUrl;

    /**
     * 是否新品
     */
    @Setter
    @Getter
    private Integer isNew;

    /**
     * 广告语
     */
    @Setter
    @Getter
    private String isNewDes;

    /**
     * 最后操作者
     */
    @Setter
    @Getter
    private Integer lastOperatorId;

    /**
     * 自动上架时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Setter
    @Getter
    private LocalDateTime autoOnlineTime;

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
     * 总时长(分钟)
     */
    @Setter
    @Getter
    private Integer totalDuration;

    /**
     * 课程列表展示图片
     */
    @Setter
    @Getter
    private String courseListImg;

    /**
     * 课程状态，0-草稿，1-上架
     */
    @Setter
    @Getter
    private Integer status;

    /**
     * 课程排序，用于后台保存草稿时用到
     */
    @Setter
    @Getter
    private Integer sortNum;

    /**
     * 课程预览第一个字段
     */
    @Setter
    @Getter
    private String previewFirstField;

    /**
     * 课程预览第二个字段
     */
    @Setter
    @Getter
    private String previewSecondField;

    /**
     * 销量
     */
    @Setter
    @Getter
    private Integer sales;

}
