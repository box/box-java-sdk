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
     *
     * @param response Response that is going to be written.
     * @param output   Output stream.
     */
    static void writeStream(BoxAPIResponse response, OutputStream output) {
        writeStream(response, output, null);
    }

    /**
     * Writes response body bytes to output stream. After all closes the input stream.
     *
     * @param response Response that is going to be written.
     * @param output   Output stream.
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
     * Writes response body bytes to output stream. After all closes the input stream.
     *
     * @param response Response that is going to be written.
     * @param output   Output stream.
     * @param listener Listener that will be notified on writing response. Can be null.
     */

    static void writeStreamWithContentLength(BoxAPIResponse response, OutputStream output, ProgressListener listener) {
        try {
            InputStream input;
            if (listener != null) {
                input = response.getBody(listener);
            } else {
                input = response.getBody();
            }
            writeStreamTo(input, output, response.getContentLength());
        } finally {
            response.close();
        }
    }

    /**
     * Writes content of input stream to provided output. Method is NOT closing input stream.
     *
     * @param input  Input that will be read.
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
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void writeStreamTo(InputStream input, OutputStream output, long expectedLength) {
        long totalBytesRead = 0;
        if (expectedLength < 0) {
            throw new RuntimeException("No Data bytes in stream");
        }
        try {
            byte[] buffer = new byte[8192];
            for (int n = input.read(buffer); n != -1; n = input.read(buffer)) {
                output.write(buffer, 0, n);
                totalBytesRead += n;  // Track the total bytes read
            }
            if (totalBytesRead != expectedLength) {
                throw new IOException("Stream ended prematurely. Expected " + expectedLength
                        + " bytes, but read " + totalBytesRead + " bytes.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error during streaming: " + e.getMessage(), e);
        } finally {
            try {
                input.close();
                output.close();
            } catch (IOException closeException) {
                throw new RuntimeException("IOException during stream close", closeException);
            }
        }
    }
}
