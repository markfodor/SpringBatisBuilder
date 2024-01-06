package org.springbatisbuilder.parser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SqlFileReader {
    private static final Logger LOGGER = Logger.getLogger(SqlFileReader.class.getName());

    private final Path filePath;

    public SqlFileReader(final Path filePath) {
        this.filePath = filePath;
    }

    public CreateTable read() throws IOException, JSQLParserException {
        final String sqlContent = Files.readString(filePath);
        final Statement statement = CCJSqlParserUtil.parse(sqlContent);

        if (statement instanceof CreateTable createTable) {
            String tableName = createTable.getTable().getName();
            LOGGER.log(Level.INFO, "Table name found: {0}",  tableName);
            return createTable;
        } else {
            throw new IOException("No 'CREATE TABLE' statement in file: " + filePath.getFileName());
        }
    }
}
