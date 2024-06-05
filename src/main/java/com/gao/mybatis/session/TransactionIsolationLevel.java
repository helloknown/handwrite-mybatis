package com.gao.mybatis.session;

import java.sql.Connection;

public enum TransactionIsolationLevel {

    // 五个事务级别
    NONE(Connection.TRANSACTION_NONE),
    READ_COMMIT(Connection.TRANSACTION_READ_COMMITTED);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
