package com.zq.interfaces;

import java.util.List;

/**
 * @InterfaceName: Dao
 * @Description: Dao类的基础接口, 规定了对数据库进行crud的几种方法, 泛型T表示的JavaBean类
 * @Author: zq007
 * @Date: 2021/5/19 13:19
 * @Version: V1.0
 */
public interface Dao<T> {
    /**
     * 用来向表中插入一条记录
     *
     * @param t 要插入到表中的JavaBean对象
     * @return 返回true, 表明插入成功, 返回false, 表明插入失败
     */
    boolean create(T t);

    /**
     * 用来根据某列字段的值来查找到所有匹配的数据
     *
     * @param fieldName 字段的名字, 可以到{@link T}的常量中获取
     * @param value  需要查找的值
     * @return 返回值是个集合, 如果返回值为null或者集合的长度为0, 则说明没有匹配的数据
     */
    List<T> retrieve(String fieldName, String value);

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据, where fileName like concat('%',?,'%')
     *
     * @param fieldName 字段的名字, 可以到{@link T}的常量中获取
     * @param keyword 需要模糊查找的关键字
     * @return  返回值是个集合, 如果返回值为null, 或者集合的长度为0, 则说明没有匹配的数据
     */
    List<T> retrieveFuzzily(String fieldName, String keyword);

    /**
     * 获取表上的所有记录
     *
     * @return 返回表上的记录所组成的集合
     */
    List<T> retrieveAll();

    /**
     * 修改一条JavaBean对象中的除了javaBeanId属性之外的其它属性中的一个属性,
     *
     * @param primaryId 需要进行修改的记录的主键id
     * @param fieldName 需要修改的字段
     * @param value 需要改成的值
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    boolean update(String primaryId, String fieldName, String value);

    /**
     * 修改一条记录中的的除了主键id字段之外的其它字段,
     *
     * @param primaryId 需要进行修改的记录的主键id
     * @param t 用来封装该记录需要修改成的数据, 不需要设值主键id
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    boolean updateExcludePrimaryId(String primaryId, T t);

    /**
     * 删除符合条件的记录
     *
     * @param fieldName 要根据哪个字段上的值来删除记录
     * @param value 指定字段上的值是value的记录要被删除
     * @return 用来表示删除了几条信息, 返回值小于1, 则说明删除失败
     */
    int delete(String fieldName, String value);
}
