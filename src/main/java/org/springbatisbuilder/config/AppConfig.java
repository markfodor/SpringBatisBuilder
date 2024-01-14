package org.springbatisbuilder.config;


import java.nio.file.Path;

public record AppConfig(
        String inputFile,
        String templateFolder,
        Path outputFolder,
        boolean useSingularModelName,
        String comment,
        String controllerPackageName,
        String mapperPackageName,
        String modelPackageName,
        String repositoryPackageName,
        String repositoryImplPackageName,
        String servicePackageName) {
}
