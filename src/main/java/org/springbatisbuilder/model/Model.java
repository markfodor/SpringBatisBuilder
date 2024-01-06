package org.springbatisbuilder.model;

import java.util.List;

public record Model(
        String tableName,
        String comment,
        String packageName,
        String classType,
        String className,
        List<Member> members) {
}
