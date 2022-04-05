package com.zq.mapper;

import com.zq.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/4 13:49
 */
public interface UserMapper {
    /**
     * 给user表中添加一条记录
     *
     * @param user
     * @return 返回值为1表示添加记录成功
     */
    @Insert("insert into user(username,age,birthday,gender,debut_works) values(#{username},#{age},#{birthday},#{gender},#{debutWorks})")
    int addUser(User user);

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    @Select({"select * from user"})
    List<User> findAll();

    /**
     * 修改指定记录的的信息
     *
     * @param user
     * @return
     */
    @Update("update user set username=#{username}, age=#{age}, gender=#{gender}, birthday=#{birthday}, debut_works=#{debutWorks} where id = #{id}")
    int updateUser(User user);

    /**
     * 根据user的id删除指定的记录
     *
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    int deleteUserById(Integer id);

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findUsserById(Integer id);

    /**
     * 查询所有用户, 与此同时查出该用户具有的所有订单
     *
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "ordersList",
                    many = @Many(select = "com.zq.mapper.OrdersMapper.findAllByUserId",
                            fetchType = FetchType.EAGER),
                    column = "id")
    })
    List<User> findAllWithOrders();

    /**
     * 查询所有用户, 与此同时查出该用户担任的所有角色
     *
     * @return
     */
    @Select("select * from user")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "sysRoleList",
                    many = @Many(select = "com.zq.mapper.SysRoleMapper.findAllByUserId",
                            fetchType = FetchType.EAGER),
                    column = "id")
    })
    List<User> findAllWithSysRoles();
}
