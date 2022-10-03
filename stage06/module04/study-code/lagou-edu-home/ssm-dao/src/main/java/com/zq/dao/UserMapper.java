package com.zq.dao;

import com.zq.domain.*;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/4 7:05
 */
public interface UserMapper {

    /**
     * 在user表中以
     * where is_del != 1 and name like concat('%', #{username}, '%') and create_time between #{startCreateTime} and {endCreateTime}
     * 为条件进行条件查询
     *
     * @param userVo
     * @return
     */
    List<User> findUserByNameAndCreateTime(UserVo userVo);

    /**
     * 更新user表中where id = #{id}多的记录的status, update_time
     *
     * @param user
     * @return
     */
    int updateUserStatus(User user);

    /**
     * 在usr表中获取一条
     * where phone = #{phone}的记录
     *
     * @param phone phone对应user表中的字段phone
     * @return 查询到的user对象
     */
    User findUserByPhone(String phone);

    /**
     * 使用内连接查询
     * 在user_role_ralation表中查找到本用户关联的所有角色的主键id
     * 根据角色的主键id在roles表中查询该角色的详细信息
     *
     * @param userId userId是user的主键id
     * @return 本用户关联的所有角色
     */
    List<Roles> findRolesAssociatedWithUserByUserId(Integer userId);

    /**
     * 在user_role_relation表中删除where user_id=#{userId}的记录, 这样该用户就不会担任这些角色了
     *
     * @param userId
     * @return
     */
    int deleteUserRoleRelationByUserId(Integer userId);

    /**
     * 在user_role_relation表中批量添加记录
     *
     * @param userRoleRelations
     * @return
     */
    int saveUserRoleRelationInBatches(UserRoleRelation... userRoleRelations);

    /**
     * 获取用户所担任角色的主键id组成的列表roleIdList
     *
     * @param userId
     * @return
     */
    List<Integer> findRoleIdListOfUser(Integer userId);

    /**
     * 获取roleIdList表示的所有角色掌握的资源
     *
     * @param roleIdList roleIdList是角色的主键id组成的集合
     * @return
     */
    List<Resource> findResourceListByRoleIdList(List<Integer> roleIdList);

    /**
     * 获取roleIdList表示的所有角色掌握的所有菜单的id组成的menuIdList
     *
     * @param roleIdList roleIdList是角色的主键id组成的集合
     * @return
     */
    List<Integer> findMenuIdListByRoleIdList(List<Integer> roleIdList);

    /**
     * 获取某个父菜单的所有子菜单对象组成的集合
     * @param parentId
     * @return
     */
    List<Menu> findSubMenuListByParentId(Integer parentId);
}
