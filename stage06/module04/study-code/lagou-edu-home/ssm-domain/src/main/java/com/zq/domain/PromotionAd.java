package com.zq.domain;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
 * @date 2022-05-30 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PromotionAd implements Serializable {

    private static final long serialVersionUID =  1662541777865351152L;

    @Setter
    @Getter
    private Integer id;

    /**
     * 广告名
     */
    @Setter
    @Getter
    private String name;

    /**
     * 广告位id
     */
    @Setter
    @Getter
    private Integer spaceId;

    /**
     * 精确搜索关键词
     */
    @Setter
    @Getter
    private String keyword;

    /**
     * 静态广告的内容
     */
    @Setter
    @Getter
    private String htmlContent;

    /**
     * 文字一
     */
    @Setter
    @Getter
    private String text;

    /**
     * 链接一
     */
    @Setter
    @Getter
    private String link;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime updateTime;

    @Setter
    @Getter
    private Integer status;

    /**
     * 优先级
     */
    @Setter
    @Getter
    private Integer priority;

    @Setter
    @Getter
    private String img;

    /**
     * ∞对1的数据库表关系中, 在 ∞ 的一方的实体类里, 会封装一个 1 的对象
     * ∞的类型是{@link PromotionAd},
     * 1的类型是{@link PromotionSpace}
     */
    @Getter
    @Setter
    private PromotionSpace promotionSpace;
}
