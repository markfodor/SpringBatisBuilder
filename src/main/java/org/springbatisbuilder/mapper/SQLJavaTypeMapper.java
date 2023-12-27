package org.springbatisbuilder.mapper;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SQLJavaTypeMapper {
    private static final Map<String, Class<?>> sqlToJavaTypeMap = new HashMap<>();

    static {
        sqlToJavaTypeMap.put("VARCHAR", String.class);
        sqlToJavaTypeMap.put("VARCHAR2", String.class);
        sqlToJavaTypeMap.put("CHAR", String.class);
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

    public static Class<?> getJavaTypeFromSQLType(final String sqlType, final boolean isKey) {
        Class<?> clazz = sqlToJavaTypeMap.get(sqlType);

        if (clazz == null) {
            throw new RuntimeException("Can not map SQL type: " + sqlType);
        }

        // force UUID if it is a primary or foreign key
        if (isKey) {
            clazz = UUID.class;
        }

        return clazz;
    }
}
