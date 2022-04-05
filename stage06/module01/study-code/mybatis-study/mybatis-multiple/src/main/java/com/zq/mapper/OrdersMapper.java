package com.zq.mapper;

import com.zq.domain.Orders;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/28 21:28
 */
public interface OrdersMapper{
    /**
     * 获取所有的表单信息, 并且每个表单对象中都有该表单的用户信息
     * @return
     */
    List<Orders> findAll();

    /**
     * 嵌套查询所有的订单信息, 且每条订单信息都携带有对应的用户信息
     * @return
     */
    List<Orders> findAll2();

    /**
     * 获取所有uid的值为指定值的订单, 这个uid是User表的主键
     * @param uid
     * @return
     */
    List<Orders> findByUid(Integer uid);
}
