package org.springbatisbuilder.generator;

import org.springbatisbuilder.model.Model;

public class MapperInterfaceGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/mapperInterface.ftl";

    public MapperInterfaceGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String javaFileName = model.className() + "Mapper.java";
        generate(model, TEMPLATE_NAME, javaFileName);
    }
}
