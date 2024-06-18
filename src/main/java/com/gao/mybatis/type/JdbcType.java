package com.gao.mybatis.type;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public enum JdbcType {

    INTEGER(Types.INTEGER),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE),
    DECIMAL(Types.DECIMAL),
    VARCHAR(Types.VARCHAR),
    CHAR(Types.CHAR),
    TIMESTAMP(Types.TIMESTAMP);

    private final int TYPE_CODE;
    private static Map<Integer, JdbcType> codeLookup = new HashMap<Integer, JdbcType>();

    static {
        for (JdbcType type : JdbcType.values()) {
            codeLookup.put(type.TYPE_CODE, type);
        }
    }

    JdbcType(int TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public static JdbcType forCode(int code) {
        return codeLookup.get(code);
    }
}
