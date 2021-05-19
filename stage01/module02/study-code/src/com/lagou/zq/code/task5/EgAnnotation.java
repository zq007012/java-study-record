package com.lagou.zq.code.task5;

import com.lagou.teacher.task5.ManType;
import com.lagou.teacher.task5.ManTypes;

import java.lang.annotation.*;

//@Retention(RetentionPolicy.SOURCE)
//@Target(ElementType.TYPE)
//@Inherited
@Repeatable(ArrayEgAnotation.class)
@interface EgAnnotation {
    String value();
    //String value2()  default "哈哈";
    //String value3();
}
