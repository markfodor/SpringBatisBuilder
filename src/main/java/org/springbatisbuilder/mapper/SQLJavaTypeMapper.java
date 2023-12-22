package org.springbatisbuilder.mapper;

import java.util.HashMap;
import java.util.Map;

public class SQLJavaTypeMapper {
    private static final Map<String, Class<?>> sqlToJavaTypeMap = new HashMap<>();

    static {
        sqlToJavaTypeMap.put("VARCHAR", String.class);
        sqlToJavaTypeMap.put("INTEGER", Integer.class);
        // TODO if primary key use UUID
        sqlToJavaTypeMap.put("INT", Integer.class);
        sqlToJavaTypeMap.put("BIGINT", Long.class);
        // TODO add more here -> date, timestamp, etc...
    }

    public static Class<?> getJavaTypeFromSQLType(final String sqlType) {
        // TODO handle if not found and throw error? -> think about it if it is enough to log an error and keep it empty
        return sqlToJavaTypeMap.get(sqlType);
    }
}
