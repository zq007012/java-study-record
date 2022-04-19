package com.zq.manager;

import com.zq.utils.ConnectionUtils;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 12:56
 */
@Component("transactionManager")
@NoArgsConstructor
public class TransactionManager {
    @Autowired
    @Qualifier("connectionUtils")
    private ConnectionUtils connectionUtils;

    public void begin() {
        try {
            connectionUtils.getConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void commit() {
        try {
            connectionUtils.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connectionUtils.getConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void release() {
        try {
            connectionUtils.getConnection().setAutoCommit(true);
            connectionUtils.getConnection().close();
            connectionUtils.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
