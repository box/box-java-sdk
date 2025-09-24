package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import org.junit.Test;

/** {@link BoxFileVersionRetention} related unit tests. */
public class BoxFileVersionRetentionTest {

  /** Unit test for {@link BoxFileVersionRetention#getInfo(String...)} */
  @Test
  public void testGetInfoSendsCorrectRequest() {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        request -> {
          assertEquals(
              "https://api.box.com/2.0/file_version_retentions/0?fields=file%2Capplied_at",
              request.getUrl().toString());
          return new BoxJSONResponse() {
            @Override
            public String getJSON() {
              return "{\"id\": \"0\"}";
            }
          };
        });

    BoxFileVersionRetention retention = new BoxFileVersionRetention(api, "0");
    retention.getInfo("file", "applied_at");
  }

  /** Unit test for {@link BoxFileVersionRetention#getInfo(String...)} */
  @Test
  public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
    final String id = "112729";
    final Date appliedAt = BoxDateFormat.parse("2015-08-06T22:02:24-07:00");
    final Date dispositionAt = BoxDateFormat.parse("2016-08-06T21:45:28-07:00");
    final String winningPolicyID = "41173";
    final String winningPolicyName = "Tax Documents";
    final String fileVersionID = "124887629";
    final String fileVersionSHA1 = "4262d6250b0e6f440dca43a2337bd4621bad9136";
    final String fileID = "5011706273";
    final String fileEtag = "2";

    final JsonObject fakeJSONResponse =
        Json.parse(
                "{\n"
                    + "    \"type\": \"file_version_retention\",\n"
                    + "    \"id\": \"112729\",\n"
                    + "    \"applied_at\": \"2015-08-06T22:02:24-07:00\",\n"
                    + "    \"disposition_at\": \"2016-08-06T21:45:28-07:00\",\n"
                    + "    \"winning_retention_policy\": {\n"
                    + "        \"type\": \"retention_policy\",\n"
                    + "        \"id\": \"41173\",\n"
                    + "        \"policy_name\": \"Tax Documents\"\n"
                    + "    },\n"
                    + "    \"file_version\": {\n"
                    + "        \"type\": \"file_version\",\n"
                    + "        \"id\": \"124887629\",\n"
                    + "        \"sha1\": \"4262d6250b0e6f440dca43a2337bd4621bad9136\"\n"
                    + "    },\n"
                    + "    \"file\": {\n"
                    + "        \"type\": \"file_version\",\n"
                    + "        \"id\": \"5011706273\",\n"
                    + "        \"etag\": \"2\"\n"
                    + "    }\n"
                    + "}")
            .asObject();

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

    BoxFileVersionRetention retention = new BoxFileVersionRetention(api, id);
    BoxFileVersionRetention.Info info = retention.getInfo();
    assertEquals(appliedAt, info.getAppliedAt());
    assertEquals(dispositionAt, info.getDispositionAt());
    assertEquals(winningPolicyID, info.getWinningPolicy().getID());
    assertEquals(winningPolicyName, info.getWinningPolicy().getPolicyName());
    assertEquals(fileVersionID, info.getFileVersion().getID());
    assertEquals(fileVersionSHA1, info.getFileVersion().getSha1());
    assertEquals(fileID, info.getFile().getID());
    assertEquals(fileEtag, info.getFile().getEtag());
  }

  /** Unit test for {@link BoxFileVersionRetention#getAll(BoxAPIConnection, String...)} */
  @Test
  public void testGetAllSendsCorrectRequest() {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        request -> {
          assertEquals(
              "https://api.box.com/2.0/file_version_retentions?limit=100",
              request.getUrl().toString());
          return new BoxJSONResponse() {
            @Override
            public String getJSON() {
              return "{\"entries\": []}";
            }
          };
        });

    BoxFileVersionRetention.getAll(api);
  }

  /**
   * Unit test for {@link BoxFileVersionRetention#getRetentions(BoxAPIConnection,
   * BoxFileVersionRetention.QueryFilter, String...)}
   */
  @Test
  public void testGetRetentionsSendsCorrectRequest() throws ParseException {
    final String urlString =
        "https://api.box.com/2.0/file_version_retentions"
            + "?file_id=0&file_version_id=1&policy_id=2"
            + "&disposition_action=permanently_delete"
            + "&disposition_before=2016-09-15T13%3A15%3A35Z"
            + "&disposition_after=2014-09-15T13%3A15%3A35Z"
            + "&fields=file%2Capplied_at"
            + "&limit=100";

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        request -> {
          try {
            assertEquals(new URL(urlString), request.getUrl());
          } catch (MalformedURLException e) {
            assert false;
          }
          return new BoxJSONResponse() {
            @Override
            public String getJSON() {
              return "{\"entries\": []}";
            }
          };
        });

    BoxFileVersionRetention.QueryFilter filter =
        new BoxFileVersionRetention.QueryFilter()
            .addFileID("0")
            .addFileVersionID("1")
            .addPolicyID("2")
            .addDispositionAction(BoxRetentionPolicy.ACTION_PERMANENTLY_DELETE)
            .addDispositionBefore(BoxDateFormat.parse("2016-09-15T13:15:35Z"))
            .addDispositionAfter(BoxDateFormat.parse("2014-09-15T13:15:35Z"));

    Iterator<BoxFileVersionRetention.Info> iterator =
        BoxFileVersionRetention.getRetentions(api, filter, "file", "applied_at").iterator();
    assertFalse(iterator.hasNext());
  }

  /** Unit test for {@link BoxFileVersionRetention#getAll(BoxAPIConnection, String...)} */
  @Test
  public void testGetAllParseAllFieldsCorrectly() {
    final String firstEntryID = "112725";
    final String secondEntryID = "112733";

    final JsonObject fakeJSONResponse =
        Json.parse(
                "{\n"
                    + "      \"entries\": [\n"
                    + "            {\n"
                    + "                \"type\": \"file_version_retention\",\n"
                    + "                \"id\": \"112725\"\n"
                    + "            },\n"
                    + "            {\n"
                    + "                \"type\": \"file_version_retention\",\n"
                    + "                \"id\": \"112733\"\n"
                    + "            }\n"
                    + "        ],\n"
                    + "        \"limit\": 100,\n"
                    + "        \"order\": [\n"
                    + "            {\n"
                    + "                \"by\": \"file_version_id\",\n"
                    + "                \"direction\": \"ASC\"\n"
                    + "            }\n"
                    + "        ]\n"
                    + "}")
            .asObject();

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

    Iterator<BoxFileVersionRetention.Info> iterator =
        BoxFileVersionRetention.getAll(api).iterator();
    BoxFileVersionRetention.Info info = iterator.next();
    assertEquals(firstEntryID, info.getID());
    info = iterator.next();
    assertEquals(secondEntryID, info.getID());
    assertFalse(iterator.hasNext());
  }
}
