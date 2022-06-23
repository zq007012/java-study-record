package com.zq.domain;

import lombok.*;

/**
 * 响应结果对象, 用来封装响应的内容
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/21 17:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    /**
     * 获取响应成功
     */
    private Boolean success;

    /**
     * 响应状态码
     */
    private Integer status;

    /**
     * 响应携带的信息
     */
    private String message;

    /**
     * 响应的内容
     */
    private Object content;
}
