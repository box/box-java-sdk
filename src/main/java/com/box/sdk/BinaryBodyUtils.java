package com.box.sdk;

import com.box.sdk.http.HttpHeaders;
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
     */

    static void writeStreamWithContentLength(BoxAPIResponse response, OutputStream output) {
        writeStreamWithContentLength(response, output, null);
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
            writeStreamTo(input, output, getContentLengthFromAPIResponse(response));
        } finally {
            response.close();
        }
    }

    /**
     * Get the content length from the API response.
     * In some cases, the Content-Length is not provided in the response headers.
     * This could happen when getting the content representation for a compressed data.
     * In that case the API will switch to chunk mode and provide the length in the "X-Original-Content-Length" header.
     *
     * @param response API response.
     * @return Content length.
     */
    private static long getContentLengthFromAPIResponse(BoxAPIResponse response) {
        long length = response.getContentLength();
        if (length == -1) {
            String headerValue = null;
            if (response.getHeaders().containsKey(HttpHeaders.CONTENT_LENGTH)) {
                headerValue = response.getHeaders().get(HttpHeaders.CONTENT_LENGTH).get(0);
            } else if (response.getHeaders().containsKey(HttpHeaders.X_ORIGINAL_CONTENT_LENGTH)) {
                headerValue = response.getHeaders().get(HttpHeaders.X_ORIGINAL_CONTENT_LENGTH).get(0);
            }

            if (headerValue != null) {
                try {
                    length = Long.parseLong(headerValue.trim());
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid content length: " + headerValue.trim() + "with: "
                        + headerValue.trim().length() + "number of characters.");
                }
            }
        }

        return length;
    }

    /**
     * Writes content of input stream to provided output.
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

    /**
     * Writes the content of the input stream to the provided output stream, ensuring the exact number of bytes specified
     * by the expected length is written. If the stream ends prematurely an exception is thrown.
     *
     * @param input          The input stream to be read.
     * @param output         The output stream where data will be written.
     * @param expectedLength The expected number of bytes to be transferred.
     */

    static void writeStreamTo(InputStream input, OutputStream output, long expectedLength) {
        long totalBytesRead = 0;
        if (expectedLength < 0) {
            throw new RuntimeException("Expected content length should not be negative: " + expectedLength);
        }
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            for (int n = input.read(buffer); n != -1; n = input.read(buffer)) {
                output.write(buffer, 0, n);
                totalBytesRead += n;  // Track the total bytes read
            }
            if (totalBytesRead != expectedLength) {
                throw new IOException("Stream ended prematurely. Expected "
                    + expectedLength + " bytes, but read " + totalBytesRead + " bytes.");
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
