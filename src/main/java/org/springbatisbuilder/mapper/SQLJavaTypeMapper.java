package org.springbatisbuilder.mapper;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SQLJavaTypeMapper {
    private static final Map<String, Class<?>> sqlToJavaTypeMap = new HashMap<>();

    static {
        sqlToJavaTypeMap.put("VARCHAR", String.class);
        sqlToJavaTypeMap.put("CHAR", String.class);
        // TODO if primary key use UUID
        sqlToJavaTypeMap.put("INTEGER", Integer.class);
        sqlToJavaTypeMap.put("INT", Integer.class);
        sqlToJavaTypeMap.put("BIGINT", Long.class);
        sqlToJavaTypeMap.put("DECIMAL", BigDecimal.class);
        sqlToJavaTypeMap.put("NUMERIC", BigDecimal.class);
        sqlToJavaTypeMap.put("BOOLEAN", Boolean.class);
        sqlToJavaTypeMap.put("DATE", Date.class);
        sqlToJavaTypeMap.put("TIME", Date.class);
        sqlToJavaTypeMap.put("TIMESTAMP", Instant.class);
        sqlToJavaTypeMap.put("BLOB", Blob.class);
        sqlToJavaTypeMap.put("CLOB", Clob.class);
    }

    public static Class<?> getJavaTypeFromSQLType(final String sqlType) {
        // TODO handle if not found and throw error? -> think about it if it is enough to log an error and keep it empty
        return sqlToJavaTypeMap.get(sqlType);
    }
}
