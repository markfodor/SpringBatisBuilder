package org.springbatisbuilder.generator;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springbatisbuilder.model.Model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/model.ftl";

    private static final Logger LOGGER = Logger.getLogger(ModelGenerator.class.getName());

    public ModelGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.className() + ".java";

        try {
            final Template template = configuration.getTemplate(TEMPLATE_NAME);
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("comment", COMMENT);
            data.put("model", model);
            template.process(data, stringWriter);

            writeToFile(stringWriter.toString(), javaFileName);
            LOGGER.info("File generated: " + javaFileName);
        } catch (final IOException | TemplateException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during the "+ javaFileName + " generation.", e);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}