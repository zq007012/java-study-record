package com.zq.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/3 20:34
 */
@NoArgsConstructor
public class UserVo {

    /**
     * 要分配给用户的角色主键id的集合
     */
    @Getter
    @Setter
    private List<Integer> roleIdList;

    /**
     * user的主键id
     */
    @Getter
    @Setter
    private Integer userId;

    /**
     * 分页查询中的当前页页码
     */
    @Getter
    @Setter
    private Integer currentPage;

    /**
     * 分页查询中的每页有几条记录
     */
    @Getter
    @Setter
    private Integer pageSize;

    /**
     * 用户名
     */
    @Getter
    @Setter
    private String username;


    /**
     * 多条件查询的条件之一, 创建用户的时间要在此之后
     */
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startCreateTime;

    /**
     * 多条件查询的条件之一, 创建用户的时间要在此之前
     */
    @Getter
    private LocalDate endCreateTime;

    /**
     * 自动为用户发来的endCreateTime加1天
     * 如果用户发来的是'2011-06-06', 那么用户期望的是'2011-06-06 23:59:59'之前的创建日期,
     * 但是`2011-06-06'到了数据库中就会被识别成'2011-06-06 00:00:00', 是满足不了用户的期望的,
     * 所以干脆将日期加1天, 就成了'2011-06-07 00:00:00', 就可以满足用户的需求了
     * @param endCreateTime
     */
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setEndCreateTime(LocalDate endCreateTime){
        this.endCreateTime = endCreateTime.plusDays(1);
    }

}
