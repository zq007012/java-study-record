package com.zq.mapper;

import com.zq.domain.Orders;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/4 15:50
 */
public interface OrdersMapper {
    /**
     * 查询所有订单, 与此同时查出该订单所属的用户
     *
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "uid", column = "uid"),
            @Result(property = "user",
                    one = @One(select = "com.zq.mapper.UserMapper.findUsserById",
                            fetchType = FetchType.EAGER),
                    column = "uid")
    })
    List<Orders> findAllWithUser();

    /**
     * 在orders表中根据uid查出所有匹配的订单
     * @param userId
     * @return
     */
    @Select("select * from orders where uid=#{userId}")
    List<Orders> findAllByUserId(Integer userId);
}
