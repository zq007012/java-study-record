package www.lagou.app;

import org.junit.Test;
import www.lagou.dao.AccountDao;
import www.lagou.entity.Account;
import www.lagou.entity.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: test01
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 8:41
 * @Version: V1.0
 */
public class Test01 {
    /**
     * 4)  按照步骤 实现卡号：1122334455向55443332211转账5000元的操作；
     * <p>
     * 步骤
     * <p>
     * a) 使用连接池创建QueryRunner对象；
     * b) 判断转出方是否有足够余额，如果不足，提示信息：”余额不足！”，并结束程序；
     * c) 通过卡号 进行转账的操作；
     * d) 转账结束后, 将转入、转出记录分别写入到Transaction表中。
     */
    @Test
    public void transactionTest() {
        try {
            //1. 获取账户当前的余额以及转账记录
            AccountDao accountDao = AccountDao.getInstance();
            Account payer = accountDao.getAccount("1122334455");
            Account payee = accountDao.getAccount("55443332211");
            printInfos(payer, payee);

            System.out.println();
            System.out.println("++++++++++++++++'" + payer.getUsername() +
                    "'准备向'" +
                    payee.getUsername() + "'转账" + 5000 + "元++++++++++++++++");
            System.out.println();

            // 2. 转账
            accountDao.transfer(payer, payee,5000.0);

            // 3. 获取账户当前的余额以及转账记录
            payer = accountDao.getAccount("1122334455");
            payee = accountDao.getAccount("55443332211");
            printInfos(payer,payee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printInfos(Account payer, Account payee) {
        System.out.println("-----------------------");
        printUserBalance(payer);
        printUserTransaction(payer);
        System.out.println("-----------------------");
        printUserBalance(payee);
        printUserTransaction(payee);
    }

    private void printUserTransaction(Account account) {
        List<Transaction> transactionList = account.getTransactions();
        int recordsCount = transactionList.size();
        if (recordsCount > 0) {
            System.out.println("'" + account.getUsername() + "'当前有"
                    + recordsCount + "条转账记录");
            System.out.println("分别是");
            for (Transaction transaction :
                    transactionList) {
                System.out.println("    \t" + transaction.toString());
            }
        } else {
            System.out.println("'" + account.getUsername() + "'当前没有转账记录");
        }
    }

    private void printUserBalance(Account account) {
        System.out.println("'" + account.getUsername() + "'当前的余额为: "
                + account.getBalance() + "元");
    }
}
