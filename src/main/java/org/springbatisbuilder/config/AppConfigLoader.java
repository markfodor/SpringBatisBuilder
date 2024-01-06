package org.springbatisbuilder.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public class AppConfigLoader {

    private static final Logger LOGGER = Logger.getLogger(AppConfigLoader.class.getName());

    private AppConfig config = null;

    private final String configPath;

    public AppConfigLoader(String configPath) {
        this.configPath = configPath;
    }

    public AppConfig load() throws IOException {
        if (config != null) {
            return config;
        }

        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        final URL configURL = AppConfigLoader.class.getResource(configPath);
        config = mapper.readValue(configURL, AppConfig.class);

        LOGGER.info("App config loaded.");

        return config;
    }
}
