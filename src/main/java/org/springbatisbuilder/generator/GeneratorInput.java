package org.springbatisbuilder.generator;

public record GeneratorInput (
        String inputTemplatePath,
        String outputFileName,
        String outputFilExtension
) {
    public String getFile() {
        return outputFileName + "." + outputFilExtension;
    }
}
