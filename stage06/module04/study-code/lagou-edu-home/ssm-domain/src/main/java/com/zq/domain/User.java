package com.zq.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @author zq007
 * @version V1.0
 * @date 2022-06-03 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    private static final long serialVersionUID =  6735329444998019111L;

    /**
     * 用户id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 用户昵称
     */
    @Setter
    @Getter
    private String name;

    /**
     * 用户头像地址
     */
    @Setter
    @Getter
    private String portrait;

    /**
     * 注册手机
     */
    @Setter
    @Getter
    private String phone;

    /**
     * 用户密码（可以为空，支持只用验证码注册、登录）
     */
    @Setter
    @Getter
    private String password;

    /**
     * 注册ip
     */
    @Setter
    @Getter
    private String regIp;

    /**
     * 是否有效用户
     */
    @Setter
    @Getter
    private Boolean accountNonExpired;

    /**
     * 账号是否未过期
     */
    @Setter
    @Getter
    private Boolean credentialsNonExpired;

    /**
     * 是否未锁定
     */
    @Setter
    @Getter
    private Boolean accountNonLocked;

    /**
     * 用户状态：ENABLE能登录，DISABLE不能登录
     */
    @Setter
    @Getter
    private String status;

    /**
     * 是否删除
     */
    @Setter
    @Getter
    private Boolean isDel;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime createTime;

    /**
     * 记录更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Setter
    @Getter
    private LocalDateTime updateTime;

}
