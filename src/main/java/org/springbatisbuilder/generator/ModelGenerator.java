package org.springbatisbuilder.generator;

import org.springbatisbuilder.model.Model;

public class ModelGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/model.ftl";

    public ModelGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.className() + ".java";
        generate(model, TEMPLATE_NAME, javaFileName);
    }
}
