package org.springbatisbuilder.generator;

import java.util.logging.Logger;

public class ControllerGenerator extends BaseGenerator {

    private static final String TEMPLATE_NAME = "java/controller.ftl";

    private static final Logger LOGGER = Logger.getLogger(ControllerGenerator.class.getName());

    public ControllerGenerator() {
        super();
    }
}
