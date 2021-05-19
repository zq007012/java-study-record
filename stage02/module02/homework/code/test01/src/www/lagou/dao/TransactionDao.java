package www.lagou.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import www.lagou.entity.Account;
import www.lagou.entity.Transaction;
import www.lagou.utils.DRUIDUtils;
import www.lagou.utils.DateUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: TransactionDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 16:41
 * @Version: V1.0
 */
public class TransactionDao {
    private static TransactionDao transactionDao;
    private QueryRunner queryRunner;
    private DataSource dataSource;

    private TransactionDao() {
        dataSource = DRUIDUtils.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    public static TransactionDao getInstance() {
        if (null == transactionDao) {
            synchronized (TransactionDao.class) {
                if (null == transactionDao) {
                    transactionDao = new TransactionDao();
                }
            }
        }
        return transactionDao;
    }

    /**
     * 根据卡号查找该卡号的转账记录
     *
     * @param card
     * @return
     */
    public List<Transaction> findRecordsByCard(String card) throws SQLException {
        String sql = "select * from transaction where cardid = ?";
        return queryRunner.query(sql, new BeanListHandler<>(Transaction.class),
                card);
    }

    /**
     * 根据账户查找用户的所有转账记录
     *
     * @param account
     * @return
     * @throws SQLException
     */
    public List<Transaction> findTransactions(Account account) throws SQLException {
        return findRecordsByCard(account.getCard());
    }

    /**
     * 添加一条转账记录
     *
     * @param account
     * @param transactionType 转账类型, 有两个值"转出", "转入"
     * @param transferMoney   转账金额
     * @return true表示转账成功, false表示转账失败
     * @throws SQLException
     */
    public boolean addTransaction(Account account, String transactionType,
                                  double transferMoney) throws SQLException {
        String sql = "insert into transaction(cardid, tratype, tramoney," +
                "tradate) values(?,?,?,?)";
        int result = queryRunner.update(sql, account.getCard(),
                transactionType, transferMoney, DateUtils.getDateTime());
        return 1 == result;

    }

    /**
     * 添加一条转账记录
     * @param conn
     * @param account
     * @param transactionType
     * @param transferMoney
     * @return
     * @throws SQLException
     */
    public boolean addTransaction(Connection conn, Account account,
                                  String transactionType,
                                  double transferMoney) throws SQLException {
        String sql = "insert into transaction(cardid, tratype, tramoney," +
                "tradate) values(?,?,?,?)";
        int result = queryRunner.update(conn, sql, account.getCard(),
                transactionType, transferMoney, DateUtils.getDateTime());
        return 1 == result;

    }
}
