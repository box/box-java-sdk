package com.box.sdk;

import static org.hamcrest.Matchers.is;

import java.net.MalformedURLException;
import java.net.URL;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class QueryStringBuilderTest {
    @Test
    public void addToUrlWithQuery() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/?sort=name&direction=DESC");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder().appendParam("limit", 20);

        URL urlWithNewParam = queryStringBuilder.addToURL(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?sort=name&direction=DESC&limit=20"));
    }

    @Test
    public void addToUrlWithEmptyQuery() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/?");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder().appendParam("limit", 20);

        URL urlWithNewParam = queryStringBuilder.addToURL(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?limit=20"));
    }

    @Test
    public void addToUrlWithoutQuery() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder().appendParam("limit", 20);

        URL urlWithNewParam = queryStringBuilder.addToURL(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?limit=20"));
    }

    @Test
    public void replacesAddsQueryWhenQueryIsEmpty() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder().appendParam("limit", 20);

        URL urlWithNewParam = queryStringBuilder.replaceQuery(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?limit=20"));
    }

    @Test
    public void replacesQuery() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/?limit=40");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder().appendParam("limit", 20);

        URL urlWithNewParam = queryStringBuilder.replaceQuery(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?limit=20"));
    }

    @Test
    public void replacesQueryWhenBuildFromExistingQuery() throws MalformedURLException {
        URL url = new URL("http://localhost:53621/folders/123456/items/?limit=40");

        QueryStringBuilder queryStringBuilder = new QueryStringBuilder("limit=20");

        URL urlWithNewParam = queryStringBuilder.replaceQuery(url);
        MatcherAssert.assertThat(urlWithNewParam.toString(),
            is("http://localhost:53621/folders/123456/items/?limit=20"));
    }
}
