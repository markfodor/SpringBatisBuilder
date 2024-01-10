package org.springbatisbuilder.converter;

import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.table.Index;
import org.apache.commons.text.CaseUtils;
import org.springbatisbuilder.mapper.SQLJavaTypeMapper;
import org.springbatisbuilder.model.Member;
import org.springbatisbuilder.model.Model;

import java.util.List;

public class CreateTableConverter {

    private final CreateTable table;

    public CreateTableConverter(final CreateTable table) {
        this.table = table;
    }

    public Model convertToModel(final String packageName, final String comment, final boolean useSingularModelName) {
        final String tableName = table.getTable().getName();
        final String className = useSingularModelName ? convertToSingularForm(tableName) : tableName;
        final String classType = useSingularModelName ? convertToSingularForm(capitalizeFirstLetter(tableName)) : capitalizeFirstLetter(tableName);
        final List<Index> indexes = table.getIndexes();

        final List<Member> members = table.getColumnDefinitions().stream()
                .map(columnDefinition -> getMember(columnDefinition, indexes))
                .toList();

        return new Model(tableName, comment, packageName, classType, className, members);
    }

    private String capitalizeFirstLetter(final String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    private String convertToSingularForm(final String text) {
        if (text != null && text.endsWith("s")) {
            return text.substring(0, text.length() - 1);
        }

        return text;
    }

    private Member getMember(final ColumnDefinition column, final List<Index> indexes) {
        final List<String> columnSpecs = column.getColumnSpecs();

        final String tableFieldName = column.getColumnName();
        final String tableFieldType = column.getColDataType().getDataType();
        final String memberName = CaseUtils.toCamelCase(column.getColumnName(), false, '_');

        final boolean isPrimaryKey = isPrivateKey(columnSpecs);
        final boolean isForeignKey = isForeignKey(tableFieldName, indexes);

        final Class<?> clazz = SQLJavaTypeMapper.getJavaTypeFromSQLType(column.getColDataType().getDataType(), isPrimaryKey || isForeignKey);

        return new Member(memberName, clazz, tableFieldName, tableFieldType, isPrimaryKey, isForeignKey);
    }

    private boolean isPrivateKey(final List<String> columnSpecs) {
        return columnSpecs != null && columnSpecs.contains("PRIMARY") && columnSpecs.contains("KEY");
    }

    private boolean isForeignKey(final String tableFieldName, final List<Index> indexes) {
        if (indexes == null) {
            return false;
        }

        return indexes.stream().anyMatch(index -> index.getType().equals("FOREIGN KEY")
                && hasMatchingColumnParam(tableFieldName, index.getColumns()));
    }

    private boolean hasMatchingColumnParam(final String param, final List<Index.ColumnParams> columnParams) {
        return columnParams.stream().anyMatch(columnParam -> columnParam.getColumnName().equals(param));
    }
}
