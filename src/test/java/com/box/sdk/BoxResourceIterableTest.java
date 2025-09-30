package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.eclipsesource.json.JsonObject;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/** {@link BoxResourceIterable} related unit tests. */
public class BoxResourceIterableTest {

  /** Unit test for {@link BoxResourceIterable.IteratorImpl#next()}. */
  @Test(expected = NoSuchElementException.class)
  public void testNextSendsCorrectRequestWithLimit() {
    final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
    final int limit = 19;

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        new RequestInterceptor() {
          @Override
          public BoxAPIResponse onRequest(BoxAPIRequest request) {
            assertEquals(
                "https://api.box.com/2.0/endpoint/0?limit=19", request.getUrl().toString());
            return new BoxJSONResponse() {
              @Override
              public String getJSON() {
                return "{\"entries\":[]}";
              }
            };
          }
        });

    Iterator<Void> iterator =
        new BoxResourceIterable<Void>(api, urlTemplate.build(api.getBaseURL(), "0"), limit) {
          @Override
          protected Void factory(JsonObject jsonObject) {
            return null;
          }
        }.iterator();

    iterator.next();
  }

  /** Unit test for {@link BoxResourceIterable.IteratorImpl#hasNext()}. */
  @Test
  public void testNextSendsCorrectRequestWithMarker() {
    final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
    final int limit = 19;

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        new RequestInterceptor() {
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

    Iterator<Void> iterator =
        new BoxResourceIterable<Void>(api, urlTemplate.build(api.getBaseURL(), "0"), limit) {
          @Override
          protected Void factory(JsonObject jsonObject) {
            return null;
          }
        }.iterator();

    iterator.next();

    api.setRequestInterceptor(
        new RequestInterceptor() {
          @Override
          public BoxAPIResponse onRequest(BoxAPIRequest request) {
            assertEquals(
                "https://api.box.com/2.0/endpoint/0?limit=19&marker=marker",
                request.getUrl().toString());
            return new BoxJSONResponse() {
              @Override
              public String getJSON() {
                return "{\"entries\":[]}";
              }
            };
          }
        });

    assertFalse(iterator.hasNext());
  }

  /** Unit test for {@link BoxResourceIterable.IteratorImpl#next()}. */
  @Test
  public void testIteratorIteratesThruEntriesCorrectly() {
    final String value1 = "1";
    final String value2 = "2";
    final String value3 = "3";
    final URLTemplate urlTemplate = new URLTemplate("endpoint/%s");
    final int limit = 19;

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        new RequestInterceptor() {
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

    Iterator<String> iterator =
        new BoxResourceIterable<String>(api, urlTemplate.build(api.getBaseURL(), "0"), limit) {
          @Override
          protected String factory(JsonObject jsonObject) {
            return jsonObject.get("field").asString();
          }
        }.iterator();

    assertTrue(iterator.hasNext());
    String field = iterator.next();
    assertEquals(value1, field);
    assertTrue(iterator.hasNext());
    field = iterator.next();
    assertEquals(value2, field);

    api.setRequestInterceptor(
        new RequestInterceptor() {
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

    assertTrue(iterator.hasNext());
    field = iterator.next();
    assertEquals(value3, field);
    assertFalse(iterator.hasNext());
  }
}
