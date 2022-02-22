package com.box.sdk;

import static java.lang.String.format;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Utility class for working with test files
 */
public final class FileUtils {

    private static final int BUFFER_SIZE = 1000;

    private FileUtils() {
        // hide constructor in utility class
    }

    /**
     * Generates file with random content. Uses BUFFER_SIZE to write generated content in batches.
     * Generated file will be removed when JVM Terminates see {@link File#deleteOnExit()}
     *
     * @param fileName        Name of the file to generate
     * @param fileSizeInBytes Size of the file to generate. Cannot be lower than BUFFER_SIZE
     * @return Generated files.
     */
    public static File generate(String fileName, int fileSizeInBytes) {
        if (fileSizeInBytes < BUFFER_SIZE) {
            throw new IllegalArgumentException(format(
                "Provides file size [%d]b must be larger than buffer [%d]b", fileSizeInBytes, BUFFER_SIZE
            ));
        }
        File file = new File(fileName);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            IntStream.range(0, fileSizeInBytes / BUFFER_SIZE + 1).forEach((i) -> {
                byte[] bytes = new byte[1000];
                new Random().nextBytes(bytes);
                try {
                    outputStream.write(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(format("Failed to create file '%s'", file), e);
        }
        file.deleteOnExit();
        return file;
    }
}
