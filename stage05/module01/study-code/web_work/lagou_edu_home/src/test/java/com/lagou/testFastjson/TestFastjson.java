package com.lagou.testFastjson;

import com.alibaba.fastjson.JSON;
import com.lagou.utils.DateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestFastjson {

    //Java对象转换为JSON
    @Test
    public void javaBeanToJSON(){
        //1.创建Person对象
        Person p = new Person("小斌",25, DateUtils.getDateFormart());

        //2.使用JSON对象 将person对象转换为 JOSN数据
        String jsonString = JSON.toJSONString(p);

        System.out.println(jsonString);//{"age":25,"birthday":"2020-07-04 15:28:03","username":"小斌"}

    }

    //Java 中的集合转JSON
    @Test
    public void ListToJSON(){

        //1.创建Person对象
        Person p1 = new Person("小斌",25, DateUtils.getDateFormart());
        Person p2 = new Person("小斌",25, DateUtils.getDateFormart());
        Person p3 = new Person("小斌",25, DateUtils.getDateFormart());

        //2.创建ArrayList集合
        List<Person> list = new ArrayList<>();

        //3.将对象封装到集合
        Collections.addAll(list,p1,p2,p3);

        //4.使用JSON对象的 toJSONString() 方法
        String jsonString = JSON.toJSONString(list);

        //[{"age":25,"birthday":"2020-07-04 15:31:30","username":"小斌"},{"age":25,"birthday":"2020-07-04 15:31:30","username":"小斌"},{"age":25,"birthday":"2020-07-04 15:31:30","username":"小斌"}]
        System.out.println(jsonString);
    }

    //JSON转对象
    @Test
    public void JSONToJavaBean(){

        String json = "{\"age\":25,\"birthday\":\"2020-07-04 15:28:03\",\"username\":\"小斌\"}";

        //使用JSON对象的 parseObject()
        Person person = JSON.parseObject(json, Person.class);
        System.out.println(person);
    }

    //JSON转List集合
    @Test
    public void JSONToList(){

        String json ="[{\"age\":25,\"birthday\":\"2020-07-04 15:31:30\",\"username\":\"小斌\"},{\"age\":25,\"birthday\":\"2020-07-04 15:31:30\",\"username\":\"小斌\"},{\"age\":25,\"birthday\":\"2020-07-04 15:31:30\",\"username\":\"小斌\"}]";

        //使用parseArray() 方法,将JSON转换为集合
        List<Person> list = JSON.parseArray(json, Person.class);

        System.out.println(list);
    }


}
