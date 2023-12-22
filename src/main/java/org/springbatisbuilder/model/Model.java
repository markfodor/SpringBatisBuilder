package org.springbatisbuilder.model;

import java.util.List;

public record Model(String packageName, String className, List<Member> members) {
}
