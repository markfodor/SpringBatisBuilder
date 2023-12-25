package org.springbatisbuilder.generator.xml;

import org.springbatisbuilder.generator.BaseGenerator;
import org.springbatisbuilder.model.Model;

public class MapperGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "xml/mapper.ftl";

    public MapperGenerator() {
        super();
    }

    public void generate(final Model model) {
        final String fileName = model.classType() + "Mapper.xml";
        generate(model, TEMPLATE_NAME, fileName);
    }
}
