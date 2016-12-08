package com.box.sdk;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxLegalHoldPolicy} related unit tests.
 */
public class BoxLegalHoldPolicyTest {

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policies/0?fields=description%2Cstatus",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        policy.getInfo("description", "status");
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "166757";
        final String name = "Policy 4";
        final String description = "Postman created policy";
        final String status = "active";
        final int assignmentsUser = 1;
        final int assignmentsFolder = 2;
        final int assignmentsFile = 3;
        final int assignmentsFileVersion = 4;
        final String userID = "2030388321";
        final String userName = "Steve Boxuser";
        final String userLogin = "steve@box.com";
        final Date createdAt = BoxDateFormat.parse("2016-05-18T10:28:45-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2016-05-18T11:25:59-07:00");
        final Date deletedAt = null;
        final Date filterStartedAt = BoxDateFormat.parse("2016-05-17T01:00:00-07:00");
        final Date filterEndedAt = BoxDateFormat.parse("2016-05-21T01:00:00-07:00");
        final String releaseNote = "non-empty note";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold_policy\",\n"
                + "  \"id\": \"166757\",\n"
                + "  \"policy_name\": \"Policy 4\",\n"
                + "  \"description\": \"Postman created policy\",\n"
                + "  \"status\": \"active\",\n"
                + "  \"assignment_counts\": {\n"
                + "    \"user\": 1,\n"
                + "    \"folder\": 2,\n"
                + "    \"file\": 3,\n"
                + "    \"file_version\": 4\n"
                + "  },\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388321\",\n"
                + "    \"name\": \"Steve Boxuser\",\n"
                + "    \"login\": \"steve@box.com\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-18T10:28:45-07:00\",\n"
                + "  \"modified_at\": \"2016-05-18T11:25:59-07:00\",\n"
                + "  \"deleted_at\": null,\n"
                + "  \"filter_started_at\": \"2016-05-17T01:00:00-07:00\",\n"
                + "  \"filter_ended_at\": \"2016-05-21T01:00:00-07:00\",\n"
                + "  \"release_notes\": \"non-empty note\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
        BoxLegalHoldPolicy.Info info = policy.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getPolicyName());
        Assert.assertEquals(description, info.getDescription());
        Assert.assertEquals(status, info.getStatus());
        Assert.assertEquals(assignmentsUser, info.getAssignmentCountUser());
        Assert.assertEquals(assignmentsFolder, info.getAssignmentCountFolder());
        Assert.assertEquals(assignmentsFile, info.getAssignmentCountFile());
        Assert.assertEquals(assignmentsFileVersion, info.getAssignmentCountFileVersion());
        Assert.assertEquals(userID, info.getCreatedBy().getID());
        Assert.assertEquals(userName, info.getCreatedBy().getName());
        Assert.assertEquals(userLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
        Assert.assertEquals(filterStartedAt, info.getFilterStartedAt());
        Assert.assertEquals(filterEndedAt, info.getFilterEndedAt());
        Assert.assertEquals(releaseNote, info.getReleaseNotes());
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#create(BoxAPIConnection, String, String, Date, Date)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateSendsCorrectJSON() throws ParseException {
        final String name = "policy";
        final String description = "some description";
        final Date startedAt = BoxDateFormat.parse("2014-05-11T00:00:00+0000");
        final Date endedAt = BoxDateFormat.parse("2016-05-11T00:00:00+0000");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policies", request.getUrl().toString());
                Assert.assertEquals(name, json.get("policy_name").asString());
                Assert.assertEquals(description, json.get("description").asString());
                try {
                    Assert.assertEquals(startedAt, BoxDateFormat.parse(json.get("filter_started_at").asString()));
                    Assert.assertEquals(endedAt, BoxDateFormat.parse(json.get("filter_ended_at").asString()));
                } catch (ParseException e) {
                    assert false;
                }
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy.create(api, name, description, startedAt, endedAt);
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#create(BoxAPIConnection, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateParseAllFieldsCorrectly() throws ParseException {
        final String id = "166921";
        final String name = "Policy 3";
        final String description = "postman created policy";
        final String userID = "2030388321";
        final String userName = "Ryan Churchill";
        final String userLogin = "rchurchill+deventerprise@box.com";
        final Date createdAt = BoxDateFormat.parse("2016-05-18T16:18:49-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2016-05-18T16:18:49-07:00");
        final Date deletedAt = null;
        final Date filterStartedAt = BoxDateFormat.parse("2016-05-11T01:00:00-07:00");
        final Date filterEndedAt = BoxDateFormat.parse("2016-05-13T01:00:00-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold_policy\",\n"
                + "  \"id\": \"166921\",\n"
                + "  \"policy_name\": \"Policy 3\",\n"
                + "  \"description\": \"postman created policy\",\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388321\",\n"
                + "    \"name\": \"Ryan Churchill\",\n"
                + "    \"login\": \"rchurchill+deventerprise@box.com\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-18T16:18:49-07:00\",\n"
                + "  \"modified_at\": \"2016-05-18T16:18:49-07:00\",\n"
                + "  \"deleted_at\": null,\n"
                + "  \"filter_started_at\": \"2016-05-11T01:00:00-07:00\",\n"
                + "  \"filter_ended_at\": \"2016-05-13T01:00:00-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldPolicy.Info info = BoxLegalHoldPolicy.create(api, name);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getPolicyName());
        Assert.assertEquals(description, info.getDescription());
        Assert.assertEquals(userID, info.getCreatedBy().getID());
        Assert.assertEquals(userName, info.getCreatedBy().getName());
        Assert.assertEquals(userLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
        Assert.assertEquals(filterStartedAt, info.getFilterStartedAt());
        Assert.assertEquals(filterEndedAt, info.getFilterEndedAt());

    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#delete()}
     */
    @Test
    @Category(UnitTest.class)
    public void testDeleteSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policies/0", request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        policy.delete();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#updateInfo(BoxLegalHoldPolicy.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateSendsCorrectJSON() {
        final String name = "new name";
        final String description = "new description";
        final String note = "new note";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policies/0", request.getUrl().toString());
                Assert.assertEquals(name, json.get("policy_name").asString());
                Assert.assertEquals(description, json.get("description").asString());
                Assert.assertEquals(note, json.get("release_note").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        BoxLegalHoldPolicy.Info info = policy.new Info();
        info.addPendingChange("policy_name", name);
        info.addPendingChange("description", description);
        info.addPendingChange("release_note", note);
        policy.updateInfo(info);
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#updateInfo(BoxLegalHoldPolicy.Info)}
     */
    @Test
    @Category(UnitTest.class)
    public void testUpdateParseAllFieldsCorrectly() throws ParseException {
        final String id = "166921";
        final String name = "New Policy 3";
        final String description = "Policy 3 New Description";
        final String userID = "2030388321";
        final String userName = "Ryan Churchill";
        final String userLogin = "rchurchill+deventerprise@box.com";
        final Date createdAt = BoxDateFormat.parse("2016-05-18T16:18:49-07:00");
        final Date modifiedAt = BoxDateFormat.parse("2016-05-18T16:20:47-07:00");
        final Date deletedAt = null;
        final Date filterStartedAt = BoxDateFormat.parse("2016-05-11T01:00:00-07:00");
        final Date filterEndedAt = BoxDateFormat.parse("2016-05-13T01:00:00-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"type\": \"legal_hold_policy\",\n"
                + "  \"id\": \"166921\",\n"
                + "  \"policy_name\": \"New Policy 3\",\n"
                + "  \"description\": \"Policy 3 New Description\",\n"
                + "  \"created_by\": {\n"
                + "    \"type\": \"user\",\n"
                + "    \"id\": \"2030388321\",\n"
                + "    \"name\": \"Ryan Churchill\",\n"
                + "    \"login\": \"rchurchill+deventerprise@box.com\"\n"
                + "  },\n"
                + "  \"created_at\": \"2016-05-18T16:18:49-07:00\",\n"
                + "  \"modified_at\": \"2016-05-18T16:20:47-07:00\",\n"
                + "  \"deleted_at\": null,\n"
                + "  \"filter_started_at\": \"2016-05-11T01:00:00-07:00\",\n"
                + "  \"filter_ended_at\": \"2016-05-13T01:00:00-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, id);
        BoxLegalHoldPolicy.Info info = policy.new Info();
        info.addPendingChange("policy_name", name);
        policy.updateInfo(info);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(name, info.getPolicyName());
        Assert.assertEquals(description, info.getDescription());
        Assert.assertEquals(userID, info.getCreatedBy().getID());
        Assert.assertEquals(userName, info.getCreatedBy().getName());
        Assert.assertEquals(userLogin, info.getCreatedBy().getLogin());
        Assert.assertEquals(createdAt, info.getCreatedAt());
        Assert.assertEquals(modifiedAt, info.getModifiedAt());
        Assert.assertEquals(deletedAt, info.getDeletedAt());
        Assert.assertEquals(filterStartedAt, info.getFilterStartedAt());
        Assert.assertEquals(filterEndedAt, info.getFilterEndedAt());

    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAll(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllSendsCorrectRequestWithoutParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policies?limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        Iterator<BoxLegalHoldPolicy.Info> iterator = BoxLegalHoldPolicy.getAll(api).iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAll(BoxAPIConnection, String, int, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/legal_hold_policies"
                                + "?policy_name=pol&fields=description%2Cstatus&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        Iterator<BoxLegalHoldPolicy.Info> iterator =
                BoxLegalHoldPolicy.getAll(api, "pol", 100, "description", "status").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAll(BoxAPIConnection)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAllParseAllFieldsCorrectly() {
        final String firstPolicyID = "166877";
        final String firstPolicyName = "Policy 1";
        final String secondPolicyID = "166881";
        final String secondPolicyName = "Policy 2";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"entries\": [\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy\",\n"
                + "      \"id\": \"166877\",\n"
                + "      \"policy_name\": \"Policy 1\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy\",\n"
                + "      \"id\": \"166881\",\n"
                + "      \"policy_name\": \"Policy 2\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"limit\": 3,\n"
                + "  \"order\": [\n"
                + "    {\n"
                + "      \"by\": \"policy_name\",\n"
                + "      \"direction\": \"ASC\"\n"
                + "    }\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        Iterator<BoxLegalHoldPolicy.Info> iterator = BoxLegalHoldPolicy.getAll(api).iterator();
        BoxLegalHoldPolicy.Info info = iterator.next();
        Assert.assertEquals(firstPolicyID, info.getID());
        Assert.assertEquals(firstPolicyName, info.getPolicyName());
        info = iterator.next();
        Assert.assertEquals(secondPolicyID, info.getID());
        Assert.assertEquals(secondPolicyName, info.getPolicyName());
        Assert.assertEquals(false, iterator.hasNext());
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#assignTo(BoxResource)}
     */
    @Test
    @Category(UnitTest.class)
    public void testAssignToSendsCorrectJSON() {
        final String policyID = "0";
        final String resourceType = BoxLegalHoldAssignment.TYPE_FILE_VERSION;
        final String resourceID = "1";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/legal_hold_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(policyID, json.get("policy_id").asString());
                Assert.assertEquals(resourceType, json.get("assign_to").asObject().get("type").asString());
                Assert.assertEquals(resourceID, json.get("assign_to").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        BoxFileVersion version = new BoxFileVersion(api, "{\"id\": \"1\"}", "2");
        policy.assignTo(version);
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAssignments(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAssignmentsSendsCorrectRequestWithFields() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/legal_hold_policies/0/assignments?fields=assigned_at&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        Iterator<BoxLegalHoldAssignment.Info> iterator = policy.getAssignments("assigned_at").iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAssignments(String, String, int, String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAssignmentsSendsCorrectRequestWithOptionalParams() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals(
                        "https://api.box.com/2.0/legal_hold_policies/0/assignments"
                                + "?assign_to_type=folder&assign_to_id=1&limit=99",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        Iterator<BoxLegalHoldAssignment.Info> iterator
            = policy.getAssignments(BoxResource.getResourceType(BoxFolder.class), "1", 99).iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getAssignments(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetAssignmentsParseAllFieldsCorrectly() {
        final String firstEntryID = "255473";
        final String secondEntryID = "123432";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"entries\": [\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy_assignment\",\n"
                + "      \"id\": \"255473\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"legal_hold_policy_assignment\",\n"
                + "      \"id\": \"123432\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"limit\": 100,\n"
                + "  \"order\": [\n"
                + "    {\n"
                + "      \"by\": \"retention_policy_id, retention_policy_object_id\",\n"
                + "      \"direction\": \"ASC\"\n"
                + "    }\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        Iterator<BoxLegalHoldAssignment.Info> iterator = policy.getAssignments().iterator();
        BoxLegalHoldAssignment.Info info = iterator.next();
        Assert.assertEquals(firstEntryID, info.getID());
        info = iterator.next();
        Assert.assertEquals(secondEntryID, info.getID());
        Assert.assertEquals(false, iterator.hasNext());
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getFileVersionHolds(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetFileVersionHoldsSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/file_version_legal_holds?policy_id=0&limit=100",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"entries\": []}";
                    }
                };
            }
        });

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        Iterator<BoxFileVersionLegalHold.Info> iterator = policy.getFileVersionHolds().iterator();
        iterator.hasNext();
    }

    /**
     * Unit test for {@link BoxLegalHoldPolicy#getFileVersionHolds(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetFileVersionHoldsParseAllFieldsCorrectly() {
        final String firstID = "241001";
        final String secondID = "241005";
        final String thirdID = "241009";

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{\n"
                + "  \"entries\": [\n"
                + "    {\n"
                + "      \"type\": \"legal_hold\",\n"
                + "      \"id\": \"241001\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"legal_hold\",\n"
                + "      \"id\": \"241005\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"type\": \"legal_hold\",\n"
                + "      \"id\": \"241009\"\n"
                + "    }\n"
                + "  ],\n"
                + "  \"limit\": 100,\n"
                + "  \"order\": [\n"
                + "    {\n"
                + "      \"by\": \"retention_policy_set_id, file_version_id\",\n"
                + "      \"direction\": \"ASC\"\n"
                + "    }\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxLegalHoldPolicy policy = new BoxLegalHoldPolicy(api, "0");
        Iterator<BoxFileVersionLegalHold.Info> iterator = policy.getFileVersionHolds().iterator();
        Assert.assertEquals(firstID, iterator.next().getID());
        Assert.assertEquals(secondID, iterator.next().getID());
        Assert.assertEquals(thirdID, iterator.next().getID());
        Assert.assertEquals(false, iterator.hasNext());
    }
}
