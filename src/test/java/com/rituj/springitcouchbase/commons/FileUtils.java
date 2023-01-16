package com.rituj.springitcouchbase.commons;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {

  public static <T> T getObjectFromFile(final String filePath, final Class<T> klass)
      throws IOException {

    var gson = new Gson();
    final var reader = Files.newBufferedReader(Paths.get(filePath));
    return gson.fromJson(reader, klass);
  }
}
