package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * A template class to build URLs from base URL, path, URL parameters and Query String.
 */
public class URLTemplate {
    private static final Pattern NUMERIC = Pattern.compile("^[0-9]*$");
    private static final Pattern ALPHA_NUMERIC = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()_+\\-]*$");
    private String template;

    /**
     * Construct an URL Template object from path.
     * @param template path
     */
    public URLTemplate(String template) {
        this.template = template;
    }

    /**
     * Build a URL with numeric URL Parameters.
     * @param base base URL
     * @param values URL parameters
     * @return URL
     */
    public URL build(String base, Object... values) {
        for (Object value: values) {
            String valueString = String.valueOf(value);
            Boolean test = NUMERIC.matcher(valueString).matches();
            if (!NUMERIC.matcher(valueString).matches()) {
                assert false : "An invalid path parameter passed in. It must be numeric.";
            }
        }
        String urlString = String.format(base + this.template, values);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "A valid URL could not be constructed from the provided parameters.";
        }

        return url;
    }

    /**
     * Build a URL with alphanumeric URL Parameters.
     * @param base base URL
     * @param values URL parameters
     * @return URL
     */
    public URL buildAlpha(String base, Object... values) {
        for (Object value: values) {
            String valueString = String.valueOf(value);
            Boolean test = ALPHA_NUMERIC.matcher(valueString).matches();
            if (!ALPHA_NUMERIC.matcher(valueString).matches()) {
                assert false : "An invalid path parameter passed in. It must be alphanumeric.";
            }
        }
        String urlString = String.format(base + this.template, values);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "A valid URL could not be constructed from the provided parameters.";
        }

        return url;
    }

    /**
     * Build a URL with Query String and numeric URL Parameters.
     * @param base base URL
     * @param queryString query string
     * @param values URL Parameters
     * @return URL
     */
    public URL buildWithQuery(String base, String queryString, Object... values) {
        for (Object value: values) {
            String valueString = String.valueOf(value);
            if (!NUMERIC.matcher(valueString).matches()) {
                assert false : "An invalid path param passed in. It must be numeric.";
            }
        }
        String urlString = String.format(base + this.template, values) + queryString;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "A valid URL could not be constructed from the provided parameters.";
        }

        return url;
    }

    /**
     * Build a URL with Query String and alphanumeric URL Parameters.
     * @param base base URL
     * @param queryString query string
     * @param values URL Parameters
     * @return URL
     */
    public URL buildAlphaWithQuery(String base, String queryString, Object... values) {
        for (Object value: values) {
            String valueString = String.valueOf(value);
            if (!ALPHA_NUMERIC.matcher(valueString).matches()) {
                assert false : "An invalid path param passed in. It must be alphanumeric.";
            }
        }
        String urlString = String.format(base + this.template, values) + queryString;
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "A valid URL could not be constructed from the provided parameters.";
        }

        return url;
    }
}
