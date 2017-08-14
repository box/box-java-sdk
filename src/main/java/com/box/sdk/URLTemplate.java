package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTemplate {
    private String template;

    URLTemplate(String template) {
        this.template = template;
    }

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
