package com.acc.sts.web.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonContentReader {
    private static InputStream fixture(String fixture) {
        return JsonContentReader.class.getResourceAsStream(fixture);
    }

    public static <T> T readJson(String filePath, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(fixture(filePath), clazz);
    }

    private JsonContentReader() {

    }
}

