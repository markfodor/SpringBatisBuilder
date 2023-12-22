package org.springbatisbuilder.generator;

import freemarker.template.Configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseGenerator {

    // TODO move this to input config
    protected static final String COMMENT = "Example comment.";

    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    private static final Logger LOGGER = Logger.getLogger(BaseGenerator.class.getName());

    private static final String OUTPUT_FOLDER = "output";

    private static final String TEMPLATE_FOLDER = "/templates";


    final Configuration configuration;

    public BaseGenerator() {
        this.configuration = new Configuration(Configuration.VERSION_2_3_30);
        this.configuration.setClassForTemplateLoading(ModelGenerator.class, TEMPLATE_FOLDER);
    }

    protected void writeToFile(String content, String fileName) throws IOException {
        createOutputFolder();

        final String filePath = Paths.get(PROJECT_ROOT, OUTPUT_FOLDER, fileName).toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private void createOutputFolder() {
        final Path dir = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Unable to create the folder: " + dir, e);
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
