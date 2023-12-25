package org.springbatisbuilder.generator.java;

import org.springbatisbuilder.generator.BaseGenerator;
import org.springbatisbuilder.model.Model;

public class ModelGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/model.ftl";

    public ModelGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String fileName = model.className() + ".java";
        generate(model, TEMPLATE_NAME, fileName);
    }
}
