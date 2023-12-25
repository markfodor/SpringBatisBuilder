package org.springbatisbuilder.generator.java;

import org.springbatisbuilder.generator.BaseGenerator;
import org.springbatisbuilder.model.Model;

public class ControllerGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/controller.ftl";

    public ControllerGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String fileName = model.classType() + "Controller.java";
        generate(model, TEMPLATE_NAME, fileName);
    }
}
