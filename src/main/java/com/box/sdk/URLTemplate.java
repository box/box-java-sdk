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

    URL build(String base, String[][] queryParams, Object... values) {
        String baseURLString = String.format(base + this.template, values);
        StringBuilder urlStringBuilder = new StringBuilder(baseURLString);
        urlStringBuilder.append("?");
        for (String[] param : queryParams) {
            urlStringBuilder.append(param[0]);
            urlStringBuilder.append('=');
            urlStringBuilder.append(param[1]);
            urlStringBuilder.append('&');
        }
        urlStringBuilder.deleteCharAt(urlStringBuilder.length() - 1);

        String urlString = urlStringBuilder.toString();
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            assert false : "An invalid URL template indicates a bug in the SDK.";
        }

        return url;
    }
}
