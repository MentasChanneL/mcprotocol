package com.prikolz.mcprotocol.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Base64Encoder {

    public static String encodeFileToBase64(String resourcePath, ClassLoader classLoader) throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
        if(inputStream == null) return null;
        return encodeFileToBase64(inputStream);
    }
    public static String encodeFileToBase64(InputStream stream) throws IOException {
        byte[] content = stream.readAllBytes();
        return Base64.getEncoder().encodeToString(content);
    }
}
