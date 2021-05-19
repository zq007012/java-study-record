package com.lagou.zq.homework.code.test3;
import java.util.*;

/*
3. 编程题

 准备一个 HashMap 集合，统计字符串"123,456,789,123,456"中每个数字字符串出现的次数并打印出来。

 如：

        123 出现了 2 次

        456 出现了 2 次

        789 出现了 1 次

 */
/*
思路: 1. 先以","为分隔符将字符串分割成一个数字字符串数组digitalStrs
      2. 创建一个Set集合digitalSet,将digtalStrs中的元素添加到digitalSet集合中,这样就会剔除掉重复的元素
      3. 创建一个HashMap集合map, map的key的类型为String, value的类型为Integer,value用来记录数字字符串
      在字符串中出现的次数
      4. 把digitalSet集合中的元素作为key添加到map集合中, 所有的key对应的value的值都是值为0的Integer对象
      5. 遍历map集合的所有key, 再遍历数组digitalStrs, 记录每个key在digitalStrs中出现的次数
      6. 打印统计结果
 */
public class Test3 {
    public static void main(String[] args) {
        //1. 先以","为分隔符将字符串分割成一个数字字符串数组digitalStrs
        String src = "123,456,789,123,456";
        String[] digitalStrs = src.split(",");

        //2. 创建一个Set集合digitalSet,将digtalStrs中的元素添加到digitalSet集合中,这样就会剔除掉重复的元素
        Set<String> digitalSet = new HashSet<>();
        digitalSet.addAll(Arrays.asList(digitalStrs));
        //System.out.println(digitalSet);
        //3. 创建一个HashMap集合map, map的key的类型为String, value的类型为Integer,value用来记录数字字符串在字符串中出现的次数
        //由于Integer类型的自动装箱功能会导致同一个数字装箱的对象是同一个,容易出错,所以干脆定义一个内部类Counter来记录次数
        HashMap<String,Counter> map = new HashMap<>();

        //4. 把digitalSet集合中的元素作为key添加到map集合中, 所有的key对应的value的值都是初始值为0的内部类Counter对象
        for (String digitalStr :
                digitalSet) {
            map.put(digitalStr, new Counter());
        }

        //5. 遍历map集合的所有key, 再遍历数组digitalStrs, 记录每个key在digitalStrs中出现的次数
        Set<String> keySet = map.keySet();
        for (String key :
                keySet) {
            System.out.println("现在统计" + key + "出现的次数...");
            for (String str :
                    digitalStrs) {
                //记录key在数组中出现的次数
                if (key.equals(str)){
                    map.get(key).count++;
                    System.out.println(key + "出现" + map.get(key).count + "次了");
                }
            }
            System.out.println(key + "的统计结束");
            System.out.println("--------------------------------------");
        }
        System.out.println("--------------统计结束-------------");

        //6. 打印统计结果
        System.out.println("字符串" + src + "中一共有" + digitalSet.size() + "种数字字符串,它们分别是:");
        for (String key :
                digitalSet) {
            System.out.println(key);
        }
        System.out.println("其中:");
        for (String key :
                digitalSet) {
            System.out.println(key + "出现了" + map.get(key).count + "次");
        }
    }

    private static class Counter {
        private int count;
        Counter(){
            count = 0;
        }
    }
}
