package org.springbatisbuilder.helper;

import org.springbatisbuilder.Main;
import org.springbatisbuilder.model.Model;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ResourceHelper {

    private ResourceHelper() { }

    public static URL getResourceURL(final String resourcePath) throws FileNotFoundException {
        final URL resource = Main.class.getResource(resourcePath);

        if (resource == null) {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }

        return resource;
    }

    public static Path getResourcePath(final String resourcePath) throws FileNotFoundException, URISyntaxException {
        final URL resourceURL = getResourceURL(resourcePath);
        return Paths.get(resourceURL.toURI());
    }

    public static String getOutputFileName(final Model model, final String outputFileEnding) {
        return model.classType() + (outputFileEnding == null ? "" : outputFileEnding);
    }
}
