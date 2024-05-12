package com.prikolz.mcprotocol.utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceReader {
    public static String read(URL resource) throws URISyntaxException, IOException {
        Path filePath = Paths.get(resource.toURI());
        return Files.readString(filePath);
    }
}
