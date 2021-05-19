package www.lagou.app;

import org.junit.Test;
import www.lagou.dao.PhoneDao;
import www.lagou.entity.Phone;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: Test02
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 18:47
 * @Version: V1.0
 */
public class Test02 {
    /**
     * 2) 需求1:  查询价格高于2000元，生产日期是2019年之前的所有手机
     */
    @Test
    public void test1() {
        try {
            String sql = "select * from phone where price > ? and prodate < ?";
            List<Phone> phones = PhoneDao.getInstance().phones(
                    sql, 2000, "2019.01.01");
            for (Phone phone :
                    phones) {
                System.out.println(phone.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 3) 需求2:  查询所有颜色是白色的手机信息
     */
    @Test
    public void test2() {
        try {
            List<Phone> phones = PhoneDao.getInstance().colorWith("白色");
            for (Phone phone :
                    phones) {
                System.out.println(phone.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
