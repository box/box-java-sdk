package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A template class to build URLs from base URL, path, URL parameters and Query String.
 */
public class URLTemplate {
    private String template;

    /**
     * Construct an URL Template object from path.
     * @param template path
     */
    public URLTemplate(String template) {
        this.template = template;
    }

    /**
     * Build a URL with URL Parameters.
     * @param base base URL
     * @param values URL parameters
     * @return URL
     */
    public URL build(String base, Object... values) {
        String urlString = String.format(base + this.template, values);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "An invalid URL template indicates a bug in the SDK.";
        }

        return url;
    }

    /**
     * Build a URL with Query String and URL Parameters.
     * @param base base URL
     * @param queryString query string
     * @param values URL Parameters
     * @return URL
     */
    public URL buildWithQuery(String base, String queryString, Object... values) {
        String urlString = String.format(base + this.template, values) + queryString;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "An invalid URL template indicates a bug in the SDK.";
        }

        return url;
    }
}
