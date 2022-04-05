package com.zq.mapper;

import com.zq.domain.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/4 16:43
 */
public interface SysRoleMapper {
    /**
     * 根据userId查找出所有匹配的角色信息
     *
     * @param userId
     * @return
     */
    @Select("select sr.* from sys_user_role sur inner join sys_role sr on sur.user_id = #{userId} and sur.role_id = sr.id")
    List<SysRole> findAllByUserId(Integer userId);
}
