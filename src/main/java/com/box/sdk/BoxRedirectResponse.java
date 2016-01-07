package com.box.sdk;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cburnette on 1/5/16.
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
     * @param  connection a connection that has already sent a request to the API.
     */
    public BoxRedirectResponse(HttpURLConnection connection) {
        super(connection);
    }

    public void setRedirectURL(URL redirectURL) {
        this.redirectURL = redirectURL;
    }

    public URL getRedirectURL() {
        return this.redirectURL;
    }

}
