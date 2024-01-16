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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Generator {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");

    private static final Logger LOGGER = Logger.getLogger(Generator.class.getName());

    private final List<GeneratorInput> inputs;

    private final Configuration configuration;

    private final Path outputFolder;

    private final Model model;

    public Generator(final String templateFolder, final Path outputFolder, final Model model) {
        this.model = model;
        this.inputs = new ArrayList<>();
        this.outputFolder = outputFolder;
        this.configuration = new Configuration(Configuration.VERSION_2_3_30);
        this.configuration.setClassForTemplateLoading(Generator.class, templateFolder);
    }

    public Generator withInput(final GeneratorInput input) {
        inputs.add(input);
        return this;
    }

    public void generate() {
        createOutputFolder();

        for (GeneratorInput input : inputs) {
            try {
                final Template template = configuration.getTemplate(input.inputTemplatePath());
                final StringWriter stringWriter = new StringWriter();
                Map<String, Object> data = new HashMap<>();
                data.put("packageName", input.packageName());
                data.put("model", model);

                template.process(data, stringWriter);
                writeToFile(stringWriter.toString(), input.getFile());
                LOGGER.log(Level.INFO, "File generated: {0}", input.getFile());
            } catch (final IOException | TemplateException e) {
                LOGGER.log(Level.SEVERE, "Error occurred during the {0} generation.", input.getFile());
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private void writeToFile(String content, String fileName) throws IOException {
        final String filePath = Paths.get(PROJECT_ROOT, outputFolder.getFileName().toString(), fileName).toString();
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    private void createOutputFolder() {
        if (Files.exists(outputFolder)) {
            deleteOutputFolderContent();
        }
        else {
            try {
                Files.createDirectory(outputFolder);
            } catch (final IOException e) {
                LOGGER.log(Level.SEVERE, "Unable to create the folder: {0}", outputFolder.getFileName());
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private void deleteOutputFolderContent() {
        try (Stream<Path> paths = Files.walk(outputFolder)) {
            paths.filter(path -> !path.equals(outputFolder))
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            LOGGER.log(Level.FINE, "File deleted: {0}", path.getFileName());
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Not able to delete file/dir: {0}", path.getFileName());
                            LOGGER.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
