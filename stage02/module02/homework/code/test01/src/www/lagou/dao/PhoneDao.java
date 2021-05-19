package www.lagou.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import www.lagou.entity.Phone;
import www.lagou.utils.DRUIDUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: PhoneDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 18:29
 * @Version: V1.0
 */
public class PhoneDao {
    private static PhoneDao phoneDao;
    private QueryRunner queryRunner;
    private DataSource dataSource;

    private PhoneDao() {
        dataSource = DRUIDUtils.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    public static PhoneDao getInstance() {
        if (null == phoneDao) {
            synchronized (PhoneDao.class) {
                if (null == phoneDao) {
                    phoneDao = new PhoneDao();
                }
            }
        }
        return phoneDao;
    }

    /**
     * 获取所有某种颜色的手机
     * @param color
     * @return
     * @throws SQLException
     */
    public List<Phone> colorWith(String color) throws SQLException {
        String sql = "select * from phone where color = ?";
        return queryRunner.query(sql, new BeanListHandler<>(Phone.class),
                color);
    }

    /**
     * 以预处理的方式执行sql语句
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Phone> phones(String sql, Object... params) throws SQLException {
        return queryRunner.query(sql, new BeanListHandler<>(Phone.class),
                params);
    }
}
