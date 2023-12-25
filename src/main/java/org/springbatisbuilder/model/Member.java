package org.springbatisbuilder.model;

public record Member(
        String name,
        Class<?> clazz,
        String tableFieldName,
        String tableFieldType,
        boolean isPrimaryKey,
        boolean isForeignKey) {
}
