package www.lagou.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import www.lagou.entity.Account;
import www.lagou.utils.DRUIDUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: AccountDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 16:15
 * @Version: V1.0
 */
public class AccountDao {
    private static AccountDao accountDao = new AccountDao();
    private QueryRunner queryRunner;
    private DataSource dataSource;
    private TransactionDao transactionDao;

    private AccountDao() {
        dataSource = DRUIDUtils.getDataSource();
        queryRunner = new QueryRunner(dataSource);
        transactionDao = TransactionDao.getInstance();
    }

    public static AccountDao getInstance() {
        return accountDao;
    }

    /**
     * 根据卡号获取这个账户的信息
     *
     * @param card
     * @return
     * @throws SQLException
     */
    public Account getAccount(String card) throws SQLException {
        String sql = "select * from account where card = ?";
        Account account = queryRunner.query(sql,
                new BeanHandler<>(Account.class), card);
        //设置庄户的转账记录
        account.setTransactions(transactionDao.findTransactions(account));
        return account;
    }

    /**
     * 从账户payer的余额转出transferMoney元
     *
     * @param conn
     * @param payer
     * @param transferMoney
     * @return
     * @throws Exception
     */
    public boolean rollOut(Connection conn, Account payer,
                           Double transferMoney)
            throws Exception {
        String sql = "update account set balance = balance - ? where " +
                "card = ?";
        int result = queryRunner.update(conn, sql, transferMoney,
                payer.getCard());
        return 1 == result;
    }

    /**
     * 向账户payee的余额转入transferMoney元
     *
     * @param conn
     * @param payee
     * @param transferMoney
     * @return
     * @throws Exception
     */
    public boolean rollIn(Connection conn, Account payee,
                          Double transferMoney)
            throws Exception {
        String sql = "update account set balance = balance + ? where " +
                "card = ?";
        int result = queryRunner.update(conn, sql, transferMoney,
                payee.getCard());
        return 1 == result;
    }

    /**
     * 账户payer向账户payee转账transferMoney元
     *
     * @param payer
     * @param payee
     * @param transferMoney
     */
    public boolean transfer(Account payer, Account payee,
                            double transferMoney) {
        boolean transferResult = false;
        System.out.println("=================开始转帐=====================");
        if (payer.getBalance() < transferMoney) {
            System.out.println("余额不足, 转账失败");
            System.out.println("=================转帐结束=====================");
            return transferResult;
        }

        // 开启一个事务, 正式开始转帐
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            // 1. 修改付款方的余额
            boolean rollOutResult = rollOut(conn, payer, transferMoney);
            if (!rollOutResult) {
                throw new Exception("付款方转出金额失败");
            }
            // 2. 为付款方添加一条转账记录
            boolean rollOutRecord = transactionDao.addTransaction(
                    conn, payer, "转出", transferMoney);
            if (!rollOutRecord) {
                throw new Exception("保存付款方的转出记录失败");
            }
            // 3. 修改收款方的余额
            boolean rollInResult = rollIn(conn, payee, transferMoney);
            if (!rollInResult) {
                throw new Exception("收款方转入金额失败");
            }
            // 4. 为收款方添加一条转账记录
            boolean rollInRecord = transactionDao.addTransaction(
                    conn, payee, "转入", transferMoney);
            if (!rollInRecord) {
                throw new Exception("保存收款方的转入记录失败");
            }

            //int a = 10 / 0;

            DbUtils.commitAndCloseQuietly(conn);
            transferResult = true;
            System.out.println("-----------转账成功!------------");
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(conn);
            System.out.println(e.getMessage());
            System.out.println("-----------因技术原因导致转账失败------------");
        } finally {
            System.out.println("=================转帐结束=====================");
        }
        return transferResult;
    }
}
