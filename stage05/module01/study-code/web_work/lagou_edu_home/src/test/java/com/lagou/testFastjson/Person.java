package com.lagou.testFastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    //可以通过 name 去指定输出的名称
    //可以使用 ordinal属性,指定字段的顺序
    //使用serialize属性,指定字段是否序列化
    @JSONField(name="USERNAME",ordinal = 1)
    private String username;

    @JSONField(name="AGE",ordinal = 2)
    private int age;

    @JSONField(serialize = false)
    private String birthday;

}
