package com.box.sdk;

import java.io.IOException;
import java.io.OutputStream;

/**
 * The callback which allows file content to be written on output stream.
 */
public interface UploadFileCallback {

    /**
     * Called when file upload api is called and requests to write content to output stream.
     *
     * @param outputStream Output stream to write file content to be uploaded.
     *
     * @throws IOException Exception while writing to output stream.
     */
    void writeToStream(OutputStream outputStream) throws IOException;
}
