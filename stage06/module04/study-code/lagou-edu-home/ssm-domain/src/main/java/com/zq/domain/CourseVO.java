package com.zq.domain;

import lombok.*;

import java.io.Serializable;

/**
 * 用来封装课程多条件查询的条件
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 10:41
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 2185198857791581117L;

    /**
     * 课程id, 值为null时是新建课程, 值不为null时是更新课程
     */
    @Getter
    @Setter
    private Integer id;

    /**
     * 课程名, 可以做为多条件查询的条件之一
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
     * 原价
     */
    @Setter
    @Getter
    private Double price;

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
     * 课程分享图片url
     */
    @Setter
    @Getter
    private String courseImgUrl;


    /**
     * 课程列表展示图片
     */
    @Setter
    @Getter
    private String courseListImg;


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
