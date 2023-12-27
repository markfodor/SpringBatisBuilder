package org.springbatisbuilder.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springbatisbuilder.model.GeneratorInput;
import org.springbatisbuilder.model.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Generator {

    // TODO move this to input config
    protected static final String COMMENT = "Example comment.";

    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    private static final Logger LOGGER = Logger.getLogger(Generator.class.getName());

    private static final String OUTPUT_FOLDER = "output";

    private static final String TEMPLATE_FOLDER = "/templates";

    private final List<GeneratorInput> inputs = new ArrayList<>();

    private final Configuration configuration;

    private final Model model;

    public Generator(final Model model) {
        this.model = model;
        this.configuration = new Configuration(Configuration.VERSION_2_3_30);
        this.configuration.setClassForTemplateLoading(Generator.class, TEMPLATE_FOLDER);
    }

    public Generator withInput(final GeneratorInput input) {
        inputs.add(input);
        return this;
    }

    public void generate() {
        for (GeneratorInput input : inputs) {
            try {
                final Template template = configuration.getTemplate(input.inputTemplatePath());
                StringWriter stringWriter = new StringWriter();
                Map<String, Object> data = new HashMap<>();
                data.put("comment", COMMENT);
                data.put("model", model);
                template.process(data, stringWriter);

                writeToFile(stringWriter.toString(), input.getFile());
                LOGGER.info("File generated: " + input.getFile());
            } catch (final IOException | TemplateException e) {
                LOGGER.log(Level.SEVERE, "Error occurred during the "+ input.getFile() + " generation.");
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private void writeToFile(String content, String fileName) throws IOException {
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
                LOGGER.log(Level.SEVERE, "Unable to create the folder: " + dir);
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
