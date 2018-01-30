package com.box.sdk;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 */
public class BoxInviteTest {

    @Test
    @Category(UnitTest.class)
    public void inviteUserToEnterpriseWorks() {

        final String userID = "13130406";
        final String userName = "Alison Wonderland";
        final String userLogin = "alice@gmail.com";
        final String enterpriseID = "119720";
        final String enterpriseName = "Australian DJs";
        final String inviterID = "029384";
        final String inviterName = "Valentino Khan";
        final String inviterLogin = "vkhan@hotmail.com";
        final String inviteID = "873465289356";
        final String status = "pending";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "    \"type\": \"invite\",\n"
                + "    \"id\": \"" + inviteID + "\",\n"
                + "    \"invited_to\": {\n"
                + "        \"type\": \"enterprise\",\n"
                + "        \"id\": \"" + enterpriseID + "\",\n"
                + "        \"name\": \"" + enterpriseName + "\"\n"
                + "    },\n"
                + "    \"actionable_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"" + userID + "\",\n"
                + "        \"name\": \"" + userName + "\",\n"
                + "        \"login\": \"" + userLogin + "\"\n"
                + "    },\n"
                + "    \"invited_by\": {\n"
                + "        \"type\": \"user\",\n"
                + "        \"id\": \"" + inviterID + "\",\n"
                + "        \"name\": \"" + inviterName + "\",\n"
                + "        \"login\": \"" + inviterLogin + "\"\n"
                + "    },\n"
                + "    \"status\": \"" + status + "\",\n"
                + "    \"created_at\": \"2014-12-23T12:55:53-08:00\",\n"
                + "    \"modified_at\": \"2014-12-23T12:55:53-08:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                final String url = request.getUrl().toString();
                if (url.equals("https://api.box.com/2.0/users/" + userID + "?fields=login")) {

                    return new BoxJSONResponse() {
                        @Override
                        public String getJSON() {
                            return "{\"type\":\"user\",\"id\":\"" + userID + "\",\"login\":\"" + userLogin + "\"}";
                        }
                    };
                } else if (url.equals("https://api.box.com/2.0/invites")) {

                    return new BoxJSONResponse() {
                        @Override
                        public String getJSON() {
                            return fakeJSONResponse.toString();
                        }
                    };
                } else {
                    fail("Unexpcted API request " + request.getUrl());
                }

                return null;
            }
        });
        BoxInvite.Info info = BoxInvite.inviteUserToEnterprise(api, userLogin, enterpriseID);

        assertEquals(inviteID, info.getID());

        BoxEnterprise enterprise = info.getInvitedTo();
        assertEquals(enterpriseID, enterprise.getID());
        assertEquals(enterpriseName, enterprise.getName());

        BoxUser.Info invited = info.getActionableBy();
        assertEquals(userID, invited.getID());
        assertEquals(userName, invited.getName());
        assertEquals(userLogin, invited.getLogin());

        BoxUser.Info inviter = info.getInvitedBy();
        assertEquals(inviterID, inviter.getID());
        assertEquals(inviterName, inviter.getName());
        assertEquals(inviterLogin, inviter.getLogin());

        assertEquals(status, info.getStatus());
    }

    @Test
    @Category(UnitTest.class)
    public void getInfoMakesCorrectCall() {

        final String id = "783645";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                assertEquals("https://api.box.com/2.0/invites/" + id,
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"type\":\"invite\",\"id\":\"" + id + "\"}";
                    }
                };
            }
        });

        BoxInvite invite = new BoxInvite(api, id);
        BoxInvite.Info info = invite.getInfo();
        assertEquals(id, info.getID());
    }
}
