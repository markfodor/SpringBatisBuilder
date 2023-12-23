package org.springbatisbuilder.generator;

import org.springbatisbuilder.model.Model;

public class RepositoryInterfaceGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/repositoryInterface.ftl";

    public RepositoryInterfaceGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.className() + "Repository.java";
        generate(model, TEMPLATE_NAME, javaFileName);
    }
}
