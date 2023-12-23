package org.springbatisbuilder.parser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class SqlFileReader {
    private static final Logger LOGGER = Logger.getLogger(SqlFileReader.class.getName());

    private final String filePath;

    public SqlFileReader(final String filePath) {
        this.filePath = filePath;
    }

    public CreateTable read() throws IOException, JSQLParserException {
        final String sqlContent = Files.readString(Paths.get(filePath));
        Statement statement = CCJSqlParserUtil.parse(sqlContent);

        if (statement instanceof CreateTable createTable) {
            String tableName = createTable.getTable().getName();
            LOGGER.info("Table Name found: " + tableName);

            return createTable;
        } else {
            LOGGER.severe("Not a CREATE TABLE statement.");
            throw new IOException();
        }
    }
}
