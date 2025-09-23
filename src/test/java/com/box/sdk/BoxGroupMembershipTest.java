package com.box.sdk;

import static org.junit.Assert.assertEquals;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;

/** {@link BoxGroupMembership} related unittests. */
public class BoxGroupMembershipTest {

  /** Unit test for {@link BoxGroupMembership#updateInfo(BoxGroupMembership.Info)}. */
  @Test
  public void testUpdateInfoSendsCorrectJson() {
    final BoxGroupMembership.GroupRole groupRole = BoxGroupMembership.GroupRole.ADMIN;

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(
        new JSONRequestInterceptor() {
          @Override
          protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
            assertEquals(
                "https://api.box.com/2.0/group_memberships/0", request.getUrl().toString());
            assertEquals(groupRole.toJSONString(), json.get("role").asString());
            return new BoxJSONResponse() {
              @Override
              public String getJSON() {
                return "{}";
              }
            };
          }
        });

    BoxGroupMembership membership = new BoxGroupMembership(api, "0");
    BoxGroupMembership.Info info = membership.new Info();
    info.setGroupRole(groupRole);
    membership.updateInfo(info);
  }

  /** Unit test for {@link BoxGroupMembership#updateInfo(BoxGroupMembership.Info)}. */
  @Test
  public void testUpdateInfoParseAllFieldsCorrectly() throws ParseException {
    final String id = "1560354";
    final String userID = "13130406";
    final String userName = "Alison Wonderland";
    final String userLogin = "alice@gmail.com";
    final String groupID = "119720";
    final String groupName = "family";
    final BoxGroupMembership.GroupRole groupRole = BoxGroupMembership.GroupRole.ADMIN;
    final Date createdAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");
    final Date modifiedAt = BoxDateFormat.parse("2013-05-16T15:27:57-07:00");

    final JsonObject fakeJSONResponse =
        Json.parse(
                "{\n"
                    + "    \"type\": \"group_membership\",\n"
                    + "    \"id\": \"1560354\",\n"
                    + "    \"user\": {\n"
                    + "        \"type\": \"user\",\n"
                    + "        \"id\": \"13130406\",\n"
                    + "        \"name\": \"Alison Wonderland\",\n"
                    + "        \"login\": \"alice@gmail.com\"\n"
                    + "    },\n"
                    + "    \"group\": {\n"
                    + "        \"type\": \"group\",\n"
                    + "        \"id\": \"119720\",\n"
                    + "        \"name\": \"family\"\n"
                    + "    },\n"
                    + "    \"role\": \"admin\",\n"
                    + "    \"created_at\": \"2013-05-16T15:27:57-07:00\",\n"
                    + "    \"modified_at\": \"2013-05-16T15:27:57-07:00\"\n"
                    + "}")
            .asObject();

    BoxAPIConnection api = new BoxAPIConnectionForTests("");
    api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));
    BoxGroupMembership membership = new BoxGroupMembership(api, id);
    BoxGroupMembership.Info info = membership.new Info();
    info.setGroupRole(BoxGroupMembership.GroupRole.ADMIN);
    membership.updateInfo(info);
    assertEquals(id, info.getID());
    assertEquals(userID, info.getUser().getID());
    assertEquals(userName, info.getUser().getName());
    assertEquals(userLogin, info.getUser().getLogin());
    assertEquals(groupID, info.getGroup().getID());
    assertEquals(groupName, info.getGroup().getName());
    assertEquals(groupRole, info.getGroupRole());
    assertEquals(createdAt, info.getCreatedAt());
    assertEquals(modifiedAt, info.getModifiedAt());
  }
}
