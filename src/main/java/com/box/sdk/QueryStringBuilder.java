package com.box.sdk;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class QueryStringBuilder {
  private final StringBuilder stringBuilder;

  QueryStringBuilder() {
    this.stringBuilder = new StringBuilder();
  }

  QueryStringBuilder(String existing) {
    if (existing == null) {
      this.stringBuilder = new StringBuilder();
    } else {
      this.stringBuilder = new StringBuilder(existing);
    }
  }

  QueryStringBuilder appendParam(String key, String... fields) {
    StringBuilder valuesBuilder = new StringBuilder();
    for (String field : fields) {
      valuesBuilder.append(field);
      valuesBuilder.append(",");
    }
    valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

    this.appendParam(key, valuesBuilder.toString());
    return this;
  }

  QueryStringBuilder appendParam(String key, String value) {
    if (this.stringBuilder.length() == 0) {
      this.stringBuilder.append('?');
    } else {
      this.stringBuilder.append('&');
    }

    this.stringBuilder.append(key);
    this.stringBuilder.append('=');
    this.stringBuilder.append(this.encode(value));
    return this;
  }

  QueryStringBuilder appendParam(String key, long value) {
    return this.appendParam(key, Long.toString(value));
  }

  URL addToURL(URL existing) throws MalformedURLException {
    QueryStringBuilder builderFromExistingUrl = new QueryStringBuilder(existing.toString());
    if (existing.getQuery() == null) {
      builderFromExistingUrl.stringBuilder.append(this.stringBuilder);
    } else {
      if (existing.getQuery().length() > 0) {
        builderFromExistingUrl.stringBuilder.append("&");
      }
      builderFromExistingUrl.stringBuilder.append(this.stringBuilder.substring(1));
    }
    return new URL(builderFromExistingUrl.toString());
  }

  URL replaceQuery(URL existing) throws MalformedURLException {
    String existingQuery = existing.getQuery();
    String newQuery = this.toString();
    if (existingQuery == null || existingQuery.equals("")) {
      return new URL(existing + newQuery);
    }

    return new URL(
        existing
            .toString()
            .replace(existingQuery, newQuery.startsWith("?") ? newQuery.substring(1) : newQuery));
  }

  @Override
  public String toString() {
    return this.stringBuilder.toString();
  }

  private String encode(String unencoded) {
    try {
      return URLEncoder.encode(unencoded, "UTF-8");
    } catch (UnsupportedEncodingException ex) {
      throw new BoxAPIException(ex.getMessage());
    }
  }
}
