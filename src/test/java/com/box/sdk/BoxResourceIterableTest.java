package com.box.sdk;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxResourceIterable} related unit tests.
 */
public class BoxResourceIterableTest {

    /**
     * Unit test for {@link BoxResourceIterable.IteratorImpl#next()}.
     */
    @Test(expected = NoSuchElementException.class)
    @Category(UnitTest.class)
    public void testNextSendsCorrectRequestWithLimit() {
        final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
        final int limit = 19;

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/endpoint/0?limit=19", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[]}";
                    }
                };
            }
        });

        Iterator<Void> iterator = new BoxResourceIterable<Void>(api, urlTemplate.build(api.getBaseURL(), "0"), limit) {
                @Override
                protected Void factory(JsonObject jsonObject) {
                    return null;
                }
            }
            .iterator();

        iterator.next();
    }

    /**
     * Unit test for {@link BoxResourceIterable.IteratorImpl#hasNext()}.
     */
    @Test
    @Category(UnitTest.class)
    public void testNextSendsCorrectRequestWithMarker() {
        final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
        final int limit = 19;

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[{\"field\": \"value\"}], \"next_marker\": \"marker\"}";
                    }
                };
            }
        });

        Iterator<Void> iterator = new BoxResourceIterable<Void>(api, urlTemplate.build(api.getBaseURL(), "0"), limit) {
                @Override
                protected Void factory(JsonObject jsonObject) {
                    return null;
                }
            }
            .iterator();

        iterator.next();

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/endpoint/0?limit=19&marker=marker", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[]}";
                    }
                };
            }
        });

        Assert.assertEquals(false, iterator.hasNext());
    }

    /**
     * Unit test for {@link BoxResourceIterable.IteratorImpl#next()}.
     */
    @Test
    @Category(UnitTest.class)
    public void testIteratorIteratesThruEntriesCorrectly() {
        final String value1 = "1";
        final String value2 = "2";
        final String value3 = "3";
        final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
        final int limit = 19;

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[{\"field\": \"1\"}, {\"field\": \"2\"}], \"next_marker\": \"marker\"}";
                    }
                };
            }
        });

        Iterator<String> iterator = new BoxResourceIterable<String>(api, urlTemplate.build(api.getBaseURL(), "0"),
                limit) {
                @Override
                protected String factory(JsonObject jsonObject) {
                    return jsonObject.get("field").asString();
                }
            }
            .iterator();

        Assert.assertEquals(true, iterator.hasNext());
        String field = iterator.next();
        Assert.assertEquals(value1, field);
        Assert.assertEquals(true, iterator.hasNext());
        field = iterator.next();
        Assert.assertEquals(value2, field);

        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\":[{\"field\": \"3\"}]}";
                    }
                };
            }
        });

        Assert.assertEquals(true, iterator.hasNext());
        field = iterator.next();
        Assert.assertEquals(value3, field);
        Assert.assertEquals(false, iterator.hasNext());
    }
}
