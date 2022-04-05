package com.zq.mapper;

import com.zq.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/25 10:23
 */
public interface UserMapper {
    List<User> findAll();
    List<User> findByArg(int age, String debutWorks);
    List<User> findByParam(int age, String debutWorks);
    List<User> findByAnnoParam(@Param("age")int age, @Param("debutWorks")String debutWorks);
    List<User> findByMap(Map<String,Object> map);

    List<User> findByPojo(User user);

    List<User> findFuzzy1(String username);
    List<User> findFuzzy2(String username);
    List<User> findFuzzy3(String username);

    /**
     * 返回值大于0表示保存成功， 返回值小于等于0表示保存失败
     * @param user
     * @return
     */
    int saveAndGetId1(User user);
    /**
     * 返回值大于0表示保存成功， 返回值小于等于0表示保存失败
     * @param user
     * @return
     */
    int saveAndGetId2(User user);

    List<User> findByIf(User user);

    List<User> findByChoose(User user);

    int updateUserBySet(User user);

    List<User> findByForeach(int[] ages);
}
