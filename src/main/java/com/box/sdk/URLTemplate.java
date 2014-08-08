package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

class URLTemplate {
    private static final String BASE_URL = "https://api.box.com/2.0/";

    private String template;

    URLTemplate(String template) {
        this.template = BASE_URL + template;
    }

    URL build(Object... values) {
        String urlString = String.format(this.template, values);

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "An invalid URL template indicates a bug in the SDK.";
        }

        return url;
    }
}
