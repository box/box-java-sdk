package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.util.Iterator;
import org.junit.Test;

/** {@link BoxFileVersionLegalHoldTest} related unit tests. */
public class BoxFileVersionLegalHoldTest {

  /** Unit test for {@link BoxFileVersionLegalHold#getInfo(String...)} */
  @Test
  public void testGetInfoSendsCorrectRequest() {
    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        request -> {
          assertEquals(
              "https://api.box.com/2.0/file_version_legal_holds/0?fields=file",
              request.getUrl().toString());
          return new BoxJSONResponse() {
            @Override
            public String getJSON() {
              return "{\"id\": \"0\"}";
            }
          };
        });

    BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(api, "0");
    hold.getInfo("file");
  }

  /** Unit test for {@link BoxFileVersionLegalHold#getInfo(String...)} */
  @Test
  public void testGetInfoParseAllFieldsCorrectly() {
    final String id = "240997";
    final String fileVersionID = "141649417";
    final String fileID = "5025122933";
    final String fileEtag = "1";
    final String firstPolicyID = "255473";
    final String secondPolicyID = "255617";

    final JsonObject fakeJSONResponse =
        Json.parse(
                "{\n"
                    + "  \"type\": \"legal_hold\",\n"
                    + "  \"id\": \"240997\",\n"
                    + "  \"file_version\": {\n"
                    + "    \"type\": \"file_version\",\n"
                    + "    \"id\": \"141649417\"\n"
                    + "  },\n"
                    + "  \"file\": {\n"
                    + "    \"type\": \"file\",\n"
                    + "    \"id\": \"5025122933\",\n"
                    + "    \"etag\": \"1\"\n"
                    + "  },\n"
                    + "  \"legal_hold_policy_assignments\": [\n"
                    + "    {\n"
                    + "      \"type\": \"legal_hold_policy_assignment\",\n"
                    + "      \"id\": \"255473\"\n"
                    + "    },\n"
                    + "    {\n"
                    + "      \"type\": \"legal_hold_policy_assignment\",\n"
                    + "      \"id\": \"255617\"\n"
                    + "    }\n"
                    + "  ],\n"
                    + "  \"deleted_at\": null\n"
                    + "}")
            .asObject();

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

    BoxFileVersionLegalHold hold = new BoxFileVersionLegalHold(api, id);
    BoxFileVersionLegalHold.Info info = hold.getInfo();
    assertEquals(id, info.getID());
    assertEquals(fileVersionID, info.getFileVersion().getID());
    assertEquals(fileID, info.getFileVersion().getFileID());
    assertEquals(fileID, info.getFile().getID());
    assertEquals(fileEtag, info.getFile().getEtag());
    assertNull(info.getDeletedAt());
    Iterator<BoxLegalHoldAssignment.Info> iterator = info.getAssignments().iterator();
    assertEquals(firstPolicyID, iterator.next().getID());
    assertEquals(secondPolicyID, iterator.next().getID());
    assertFalse(iterator.hasNext());
  }
}
