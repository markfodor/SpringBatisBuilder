package org.springbatisbuilder;


import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springbatisbuilder.config.AppConfig;
import org.springbatisbuilder.config.AppConfigLoader;
import org.springbatisbuilder.converter.CreateTableConverter;
import org.springbatisbuilder.generator.Generator;
import org.springbatisbuilder.generator.GeneratorInput;
import org.springbatisbuilder.helper.ResourceHelper;
import org.springbatisbuilder.model.Model;
import org.springbatisbuilder.parser.SqlFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER;
    public static final String CONFIG_YAML = "/config.yaml";

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(Main.class.getName());
        LOGGER.setLevel(Level.ALL);
    }

    public static void main(String[] args) throws IOException, JSQLParserException, URISyntaxException {

        final AppConfig config = new AppConfigLoader(CONFIG_YAML).load();
        final Path inputFilePath = ResourceHelper.getResourcePath(config.inputFile());
        final CreateTable createTable = new SqlFileReader(inputFilePath).read();
        final Model model = new CreateTableConverter(createTable).convertToModel(config.packageName(), config.comment());

        LOGGER.info("Table mapped.");

        final Generator generator = new Generator(config.templateFolder(), config.outputFolder(), model)
                .withInput(new GeneratorInput("java/model.ftl", ResourceHelper.getOutputFileName(model, null), "java"))
                .withInput(new GeneratorInput("java/controller.ftl", ResourceHelper.getOutputFileName(model, "Controller"), "java"))
                .withInput(new GeneratorInput("java/mapperInterface.ftl", ResourceHelper.getOutputFileName(model, "Mapper"), "java"))
                .withInput(new GeneratorInput("java/repositoryInterface.ftl", ResourceHelper.getOutputFileName(model, "Repository"), "java"))
                .withInput(new GeneratorInput("java/repositoryImpl.ftl", ResourceHelper.getOutputFileName(model, "RepositoryImpl"), "java"))
                .withInput(new GeneratorInput("java/service.ftl", ResourceHelper.getOutputFileName(model, "Service"), "java"))
                .withInput(new GeneratorInput("xml/mapper.ftl", ResourceHelper.getOutputFileName(model, "Mapper"), "xml"));

        LOGGER.info("Templates added.");

        generator.generate();
        LOGGER.info("Done.");
    }
}