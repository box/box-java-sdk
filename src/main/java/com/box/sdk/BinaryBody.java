package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility class to help writing binary data to output stream.
 */
final class BinaryBody {
    private static final int BUFFER_SIZE = 8192;

    private BinaryBody() {
        // utility class has no public constructor
    }

    static void writeStream(BoxAPIResponse response, OutputStream output) {
        writeStream(response, output, null);
    }

    static void writeStream(BoxAPIResponse response, OutputStream output, ProgressListener listener) {
        try {
            InputStream input;
            if (listener != null) {
                input = response.getBody(listener);
            } else {
                input = response.getBody();
            }
            byte[] buffer = new byte[BUFFER_SIZE];
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        } finally {
            response.disconnect();
        }
    }
}
