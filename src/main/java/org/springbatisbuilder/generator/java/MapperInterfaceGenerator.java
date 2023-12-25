package org.springbatisbuilder.generator.java;

import org.springbatisbuilder.generator.BaseGenerator;
import org.springbatisbuilder.model.Model;

public class MapperInterfaceGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/mapperInterface.ftl";

    public MapperInterfaceGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String fileName = model.className() + "Mapper.java";
        generate(model, TEMPLATE_NAME, fileName);
    }
}
