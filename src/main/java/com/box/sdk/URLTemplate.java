package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

class URLTemplate {
    private String template;

    URLTemplate(String template) {
        this.template = template;
    }

    URL build(String base, Object... values) {
        String urlString = String.format(base + this.template, values);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "An invalid URL template indicates a bug in the SDK.";
        }

        return url;
    }
}
