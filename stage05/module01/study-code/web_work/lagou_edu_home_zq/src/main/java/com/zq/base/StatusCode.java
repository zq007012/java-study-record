package com.zq.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

/**
 * 用来表示不同状态码的枚举类
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/4 18:06
 */
public enum StatusCode {
    /**
     * 表示消息发送成功的枚举对象
     */
    SUCCESS("success", 0),
    /**
     * 表示消息发送失败的枚举对象
     */
    FAIL("fail",1);

    @Getter
    private final String msg;

    @Getter
    private final int status;

    private StatusCode(String msg, int status){
        this.msg = msg;
        this.status = status;
    }

    /**
     * 将枚举对象转化成Json字符串,格式为: {"msg":"success","status":0}
     * @return
     */
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",this.getMsg());
        jsonObject.put("status",this.getStatus());
        return jsonObject.toString();
    }


}
