package org.springbatisbuilder.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springbatisbuilder.model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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

    protected void generate(final Model model, final String templateName, final String fileName) {
        try {
            final Template template = configuration.getTemplate(templateName);
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("comment", COMMENT);
            data.put("model", model);
            template.process(data, stringWriter);

            writeToFile(stringWriter.toString(), fileName);
            LOGGER.info("File generated: " + fileName);
        } catch (final IOException | TemplateException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during the "+ fileName + " generation.", e);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
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
