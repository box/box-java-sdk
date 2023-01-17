package com.box.sdk;

import java.net.URL;
import okhttp3.MediaType;

/**
 * <p>Used to make HTTP multipart requests to the Box API.</p>
 *
 * <p>This class partially implements the HTTP multipart standard in order to upload files to Box. The body of this
 * request type cannot be set directly. Instead, it can be modified by adding multipart fields and setting file
 * contents. The body of multipart requests will not be logged since they are likely to contain binary data.</p>
 */
public class BoxMultipartRequest extends AbstractBoxMultipartRequest {

    /**
     * Constructs an authenticated BoxMultipartRequest using a provided BoxAPIConnection.
     *
     * @param api an API connection for authenticating the request.
     * @param url the URL of the request.
     */
    public BoxMultipartRequest(BoxAPIConnection api, URL url) {
        super(api, url);
    }

    @Override
    protected String getPartName() {
        return "file";
    }

    @Override
    protected MediaType getPartContentType(String filename) {
        return MediaType.parse("application/octet-stream");
    }
}
