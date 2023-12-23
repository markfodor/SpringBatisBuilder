package org.springbatisbuilder.generator;

import org.springbatisbuilder.model.Model;

public class RespositoryImplGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/repositoryImpl.ftl";

    public RespositoryImplGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.className() + "RepositoryImpl.java";
        generate(model, TEMPLATE_NAME, javaFileName);
    }
}
