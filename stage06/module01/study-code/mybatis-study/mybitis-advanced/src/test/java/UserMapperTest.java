import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.domain.User;
import com.zq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/25 10:29
 */
public class UserMapperTest {
    private SqlSession sqlSession;

    {
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试{@link UserMapper#findByGenderAndDebutWorks(String, String)}方法的功能
     */
    @Test
    public void findByGenderAndDebutWorksTest(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findByGenderAndDebutWorks("女");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void findAllTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();

        System.out.println(users);
    }

    @Test
    public void findByArgTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findByArg(35,"海贼王");

        System.out.println(users);
    }
    @Test
    public void findByParamTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findByParam(35,"海贼王");

        System.out.println(users);
    }

    @Test
    public void findByAnnoParamTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findByAnnoParam(35,"海贼王");

        System.out.println(users);
    }
    @Test
    public void findByMapTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap();
        map.put("age", 35);
        map.put("debutWorks","海贼王");
        List<User> users = userMapper.findByMap(map);

        System.out.println(users);
    }

    @Test
    public void findByPojoTest() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(35);
        user.setDebutWorks("海贼王");
        List<User> users = userMapper.findByPojo(user);

        System.out.println(users);
    }

    @Test
    public void findFuzzy1() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //List<User> users = userMapper.findFuzzy1("%法%");
        List<User> users = userMapper.findFuzzy1("%法% or 1=1");

        System.out.println(users);
    }

    @Test
    public void findFuzzy2() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //List<User> users = userMapper.findFuzzy2("法");
        List<User> users = userMapper.findFuzzy2("法' or 1 like '");

        System.out.println(users);
    }

    @Test
    public void findFuzzy3() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //List<User> users = userMapper.findFuzzy3("法");
        List<User> users = userMapper.findFuzzy3("法' or 1 like '");

        System.out.println(users);
    }

    /**
     * 测试{@link UserMapper#saveAndGetId1(User)}方法的功能
     */
    @Test
    public void saveAndGetId1Test(){
        User user = new User();
        user.setUsername("蒙奇·D·路飞");
        user.setAge(22);
        user.setBirthday("2000-09-09");
        user.setGender("男");
        user.setDebutWorks("海贼王");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(user.getId());
        int row = mapper.saveAndGetId1(user);
        sqlSession.commit();
        System.out.println(user.getId());

    }

    /**
     * 测试{@link UserMapper#saveAndGetId1(User)}方法的功能
     */
    @Test
    public void saveAndGetId2Test() {
        User user = new User();
        user.setUsername("托尼托尼·乔巴");
        user.setAge(18);
        user.setBirthday("2004-02-02");
        user.setGender("男");
        user.setDebutWorks("海贼王");

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(user.getId());
        int row = mapper.saveAndGetId2(user);
        sqlSession.commit();
        System.out.println(user.getId());

    }

    /**
     * 测试{@link UserMapper#findByIf(User)}方法的功能
     */
    @Test
    public void findByIfTest(){
        User user = new User();
        user.setId(15);
        user.setUsername("");
        user.setDebutWorks("");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByIf(user);
        System.out.println(users);
    }

    /**
     * 测试{@link UserMapper#findByChoose(User)}方法的功能
     */
    @Test
    public void findByChooseTest(){
        User user = new User();
        user.setId(0);
        user.setUsername("");
        user.setDebutWorks("海贼王");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByChoose(user);
        System.out.println(users);
    }

    /**
     * 测试{@link UserMapper#updateUserBySet(User)}方法的功能
     */
    @Test
    public void updateUserBySet(){
        User user = new User();
        user.setId(106);
        user.setUsername("");
        user.setAge(20);
        user.setBirthday("2002-02-02");
        user.setDebutWorks("");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int rows = mapper.updateUserBySet(user);
        sqlSession.commit();
        System.out.println(rows);
    }

    /**
     * 测试{@link UserMapper#findByForeach(int[])}方法的功能
     */
    @Test
    public void findByForeachTest(){
        int[] ages = {22, 27, 35};
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByForeach(ages);
        System.out.println(users);
    }

    /**
     * 测试分页助手插件的功能
     */
    @Test
    public void pageHelperTest(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(2,5);
        List<User> users = mapper.findAll();
        System.out.println("第一次查: ");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("第二次查: ");
        List<User> users2 = mapper.findAll();
        for (User user : users2) {
            System.out.println(user);
        }

        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println("本来该查询到的总条数: " + userPageInfo.getTotal());
        System.out.println("总页数: " + userPageInfo.getPages());
        System.out.println("当前是第几页: " + userPageInfo.getPageNum());
        System.out.println("每页有多少条记录: " + userPageInfo.getPageSize());
        System.out.println("当前是否是第一页: " + userPageInfo.isIsFirstPage());
        System.out.println("当前是否是最后一页: " + userPageInfo.isIsLastPage());
    }

}
