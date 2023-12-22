package org.springbatisbuilder;


import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springbatisbuilder.converter.CreateTableConverter;
import org.springbatisbuilder.generator.ModelGenerator;
import org.springbatisbuilder.generator.RepositoryInterfaceGenerator;
import org.springbatisbuilder.model.Model;
import org.springbatisbuilder.parser.SqlFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    // TODO move this to input config
    private static final String packageName = "com.example";

    private static final Logger LOGGER;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(Main.class.getName());
        LOGGER.setLevel(Level.ALL);
    }

    // TODO handle exceptions -> common generator class
    public static void main(String[] args) throws IOException, JSQLParserException, URISyntaxException {
        // TODO move this to input config
        final String filePath = getResourceAbsolutePath("/example.sql");
        // TODO handle errors from casting and different DDL options
        final CreateTable createTable = (new SqlFileReader(filePath)).read();
        final Model model = CreateTableConverter.convertToPojo(createTable, packageName);
        // TODO use a common generator
        new ModelGenerator().generate(model);
        new RepositoryInterfaceGenerator().generate(model);

        LOGGER.info("Done.");
    }

    private static String getResourceAbsolutePath(final String fileName) throws URISyntaxException, FileNotFoundException {
        final URL resource = Main.class.getResource(fileName);

        if (resource == null) {
            throw new FileNotFoundException("Resource not found: " + fileName);
        }

        return Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }

}