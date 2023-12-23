package org.springbatisbuilder.generator;

import org.springbatisbuilder.model.Model;

public class ServiceGenerator  extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/service.ftl";

    public ServiceGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.classType() + "Service.java";
        generate(model, TEMPLATE_NAME, javaFileName);
    }
}
