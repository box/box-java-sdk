package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class to help writing binary data to output stream.
 */
final class BinaryBodyUtils {
    private static final int BUFFER_SIZE = 8192;

    private BinaryBodyUtils() {
        // utility class has no public constructor
    }

    /**
     * Writes response body bytes to output stream. After all closes the input stream.
     * @param response Response that is going to be written.
     * @param output Output stream.
     */
    static void writeStream(BoxAPIResponse response, OutputStream output) {
        writeStream(response, output, null);
    }

    /**
     * Writes response body bytes to output stream. After all closes the input stream.
     * @param response Response that is going to be written.
     * @param output Output stream.
     * @param listener Listener that will be notified on writing response. Can be null.
     */

    static void writeStream(BoxAPIResponse response, OutputStream output, ProgressListener listener) {
        try {
            InputStream input;
            if (listener != null) {
                input = response.getBody(listener);
            } else {
                input = response.getBody();
            }
            writeStreamTo(input, output);
        } finally {
            response.close();
        }
    }

    /**
     * Writes content of input stream to provided output. Method is NOT closing input stream.
     * @param input Input that will be read.
     * @param output Output stream.
     */
    static void writeStreamTo(InputStream input, OutputStream output) {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
