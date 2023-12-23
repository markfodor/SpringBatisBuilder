package org.springbatisbuilder.converter;

import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.apache.commons.text.CaseUtils;
import org.springbatisbuilder.mapper.SQLJavaTypeMapper;
import org.springbatisbuilder.model.Member;
import org.springbatisbuilder.model.Model;

import java.util.List;
import java.util.stream.Collectors;

public class CreateTableConverter {
    public static Model convertToModel(final CreateTable table, final String packageName) {
        final String classType = capitalizeFirstLetter(table.getTable().getName());
        final String className = table.getTable().getName();
        final List<Member> members = table.getColumnDefinitions().stream()
                .map(CreateTableConverter::getMember)
                .collect(Collectors.toList());

        return new Model(packageName, classType, className, members);
    }

    private static String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    private static Member getMember(final ColumnDefinition column) {
        final String memberName = CaseUtils.toCamelCase(column.getColumnName(), false, '_');
        final Class<?> clazz = SQLJavaTypeMapper.getJavaTypeFromSQLType(column.getColDataType().getDataType());

        final List<String> columnSpecs = column.getColumnSpecs();
        final boolean isPrimaryKey = columnSpecs != null && columnSpecs.contains("KEY") && columnSpecs.contains("PRIMARY");
        final boolean isForeignKey = false; // TODO

        return new Member(memberName, clazz, isPrimaryKey, isForeignKey);
    }
}
