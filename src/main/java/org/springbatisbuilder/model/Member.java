package org.springbatisbuilder.model;

public record Member(String name, Class<?> clazz, boolean isPrimaryKey, boolean isForeignKey) {
}
