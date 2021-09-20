package com.box.sdk;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Used to read HTTP responses containing redirect URLs in the 'LOCATION' header.
 *
 * <p>This request type extends BoxAPIResponse to provide additional functionality for handling redirect URLs.</p>
 */
public class BoxRedirectResponse extends BoxAPIResponse {

    private URL redirectURL;

    /**
     * Constructs a BoxRedirectResponse without an associated HttpURLConnection.
     */
    public BoxRedirectResponse() {
        super();
    }

    /**
     * Constructs a BoxRedirectResponse using an HttpURLConnection.
     *
     * @param connection a connection that has already sent a request to the API.
     */
    public BoxRedirectResponse(HttpURLConnection connection) {
        super(connection);
    }

    /**
     * Gets the redirect URL for this response.
     *
     * @return redirectURL
     */
    public URL getRedirectURL() {
        return this.redirectURL;
    }

    /**
     * Sets the redirectURL for this response.
     *
     * @param redirectURL the redirect URL
     */
    public void setRedirectURL(URL redirectURL) {
        this.redirectURL = redirectURL;
    }

}
