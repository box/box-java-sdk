package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxRetentionPolicyAssignment} related unit tests.
 */
public class BoxRetentionPolicyAssignmentTest {

    /**
     * Unit test for {@link BoxRetentionPolicyAssignment#createAssignmentToEnterprise(BoxAPIConnection, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateAssignmentToEnterpriseSendsCorrectJson() {
        final String id = "0";
        final String type = "enterprise";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/retention_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(id, json.get("policy_id").asString());
                Assert.assertEquals(type, json.get("assign_to").asObject().get("type").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxRetentionPolicyAssignment.createAssignmentToEnterprise(api, id);
    }

    /**
     * Unit test for {@link BoxRetentionPolicyAssignment#createAssignmentToFolder(BoxAPIConnection, String, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateAssignmentToFolderSendsCorrectJson() {
        final String id = "0";
        final String type = "folder";
        final String folderID = "1";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            protected BoxAPIResponse onJSONRequest(BoxJSONRequest request, JsonObject json) {
                Assert.assertEquals("https://api.box.com/2.0/retention_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals(id, json.get("policy_id").asString());
                Assert.assertEquals(type, json.get("assign_to").asObject().get("type").asString());
                Assert.assertEquals(folderID, json.get("assign_to").asObject().get("id").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxRetentionPolicyAssignment.createAssignmentToFolder(api, id, folderID);
    }

    /**
     * Unit test for {@link BoxRetentionPolicyAssignment#createAssignmentToFolder(BoxAPIConnection, String, String)}
     */
    @Test
    @Category(UnitTest.class)
    public void testCreateAssignmentParseAllFieldsCorrectly() throws ParseException {
        final String id = "3233225";
        final String policyID = "32131";
        final String policyName = "TaxDocuments";
        final String assignedToType = "folder";
        final String assignedToID = "99922219";
        final String assignedByID = "123456789";
        final String assignedByName = "Sean";
        final String assignedByLogin = "sean@box.com";
        final Date assignedAt = BoxDateFormat.parse("2015-07-20T14:28:09-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{     \n"
                + "  \"type\": \"retention_policy_assignment\",     \n"
                + "  \"id\": \"3233225\",     \n"
                + "  \"retention_policy\": {         \n"
                + "    \"type\": \"retention_policy\",         \n"
                + "    \"id\": \"32131\",         \n"
                + "    \"policy_name\": \"TaxDocuments\"     \n"
                + "  },\n"
                + "  \"assigned_to\": {\n"
                + "    \"type\": \"folder\",         \n"
                + "    \"id\": \"99922219\"     \n"
                + "  },     \n"
                + "  \"assigned_by\": {         \n"
                + "    \"type\": \"user\",        \n"
                + "    \"id\": \"123456789\",        \n"
                + "    \"name\": \"Sean\",        \n"
                + "    \"login\": \"sean@box.com\"    \n"
                + "  },    \n"
                + "  \"assigned_at\": \"2015-07-20T14:28:09-07:00\"\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxRetentionPolicyAssignment.Info info
            = BoxRetentionPolicyAssignment.createAssignmentToFolder(api, policyID, assignedToID);
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(policyID, info.getRetentionPolicy().getID());
        Assert.assertEquals(policyName, info.getRetentionPolicy().getPolicyName());
        Assert.assertEquals(assignedToType, info.getAssignedToType());
        Assert.assertEquals(assignedToID, info.getAssignedToID());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
    }


    /**
     * Unit test for {@link BoxRetentionPolicyAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoSendsCorrectRequest() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public BoxAPIResponse onRequest(BoxAPIRequest request) {
                Assert.assertEquals("https://api.box.com/2.0/retention_policy_assignments/0?fields=assigned_to",
                        request.getUrl().toString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\"}";
                    }
                };
            }
        });

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(api, "0");
        assignment.getInfo("assigned_to");
    }

    /**
     * Unit test for {@link BoxRetentionPolicyAssignment#getInfo(String...)}
     */
    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseAllFieldsCorrectly() throws ParseException {
        final String id = "3233225";
        final String policyID = "32131";
        final String policyName = "TaxDocuments";
        final String assignedToType = "folder";
        final String assignedToID = "99922219";
        final String assignedByID = "123456789";
        final String assignedByName = "Sean";
        final String assignedByLogin = "sean@box.com";
        final Date assignedAt = BoxDateFormat.parse("2015-07-20T14:28:09-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{     \n"
                + "  \"type\": \"retention_policy_assignment\",    \n"
                + "  \"id\": \"3233225\",    \n"
                + "  \"retention_policy\": {   \n"
                + "    \"type\": \"retention_policy\",    \n"
                + "    \"id\": \"32131\",   \n"
                + "    \"policy_name\": \"TaxDocuments\"\n"
                + "  },    \n"
                + "  \"assigned_to\": {  \n"
                + "    \"type\": \"folder\",   \n"
                + "    \"id\": \"99922219\"   \n"
                + "  },   \n"
                + "  \"assigned_by\": {  \n"
                + "    \"type\": \"user\",    \n"
                + "    \"id\": \"123456789\",  \n"
                + "    \"name\": \"Sean\",     \n"
                + "    \"login\": \"sean@box.com\"     \n"
                + "  },     \n"
                + "  \"assigned_at\": \"2015-07-20T14:28:09-07:00\" \n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(api, id);
        BoxRetentionPolicyAssignment.Info info = assignment.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(policyID, info.getRetentionPolicy().getID());
        Assert.assertEquals(policyName, info.getRetentionPolicy().getPolicyName());
        Assert.assertEquals(assignedToType, info.getAssignedToType());
        Assert.assertEquals(assignedToID, info.getAssignedToID());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
    }

    @Test
    @Category(UnitTest.class)
    public void testGetInfoParseMetadataFieldsCorrectly() throws ParseException {
        final String id = "3233225";
        final String policyID = "32131";
        final String policyName = "TaxDocuments";
        final String assignedToType = "metadata_template";
        final String assignedToID = "enterprise.myTemplate";
        final String assignedByID = "123456789";
        final String assignedByName = "Sean";
        final String assignedByLogin = "sean@box.com";
        final Date assignedAt = BoxDateFormat.parse("2015-07-20T14:28:09-07:00");

        final JsonObject fakeJSONResponse = JsonObject.readFrom("{     \n"
                + "  \"type\": \"retention_policy_assignment\",    \n"
                + "  \"id\": \"" + id + "\",    \n"
                + "  \"retention_policy\": {   \n"
                + "    \"type\": \"retention_policy\",    \n"
                + "    \"id\": \"" + policyID + "\",   \n"
                + "    \"policy_name\": \"" + policyName + "\"\n"
                + "  },    \n"
                + "  \"assigned_to\": {  \n"
                + "    \"type\": \"" + assignedToType + "\",   \n"
                + "    \"id\": \"" + assignedToID + "\"   \n"
                + "  },   \n"
                + "  \"assigned_by\": {  \n"
                + "    \"type\": \"user\",    \n"
                + "    \"id\": \"" + assignedByID + "\",  \n"
                + "    \"name\": \"" + assignedByName + "\",     \n"
                + "    \"login\": \"" + assignedByLogin + "\"     \n"
                + "  },     \n"
                + "  \"assigned_at\": \"2015-07-20T14:28:09-07:00\",\n"
                + "  \"filter_fields\": [\n"
                + "    {\n"
                + "      \"field\": \"foo\",\n"
                + "      \"value\": \"bar\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"field\": \"baz\",\n"
                + "      \"value\": 123\n"
                + "    }\n"
                + "  ]\n"
                + "}");

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(JSONRequestInterceptor.respondWith(fakeJSONResponse));

        BoxRetentionPolicyAssignment assignment = new BoxRetentionPolicyAssignment(api, id);
        BoxRetentionPolicyAssignment.Info info = assignment.getInfo();
        Assert.assertEquals(id, info.getID());
        Assert.assertEquals(policyID, info.getRetentionPolicy().getID());
        Assert.assertEquals(policyName, info.getRetentionPolicy().getPolicyName());
        Assert.assertEquals(assignedToType, info.getAssignedToType());
        Assert.assertEquals(assignedToID, info.getAssignedToID());
        Assert.assertEquals(assignedByID, info.getAssignedBy().getID());
        Assert.assertEquals(assignedByName, info.getAssignedBy().getName());
        Assert.assertEquals(assignedByLogin, info.getAssignedBy().getLogin());
        Assert.assertEquals(assignedAt, info.getAssignedAt());
        Assert.assertEquals("metadata_template", info.getAssignedToType());
        Assert.assertEquals("enterprise.myTemplate", info.getAssignedToID());

        MetadataFieldFilter filter1 = info.getFilterFields().get(0);
        Assert.assertEquals("foo", filter1.getJsonObject().get("field").asString());
        Assert.assertEquals("bar", filter1.getJsonObject().get("value").asString());

        MetadataFieldFilter filter2 = info.getFilterFields().get(1);
        Assert.assertEquals("baz", filter2.getJsonObject().get("field").asString());
        Assert.assertEquals(123, filter2.getJsonObject().get("value").asInt());
    }

    @Test
    @Category(UnitTest.class)
    public void testCreateAssignmentToMetadataMakesCorrectRequest() {

        final String policyID = "1234";
        final String templateID = "9b80ed1d-df05-41db-840a-38fc77c5fe0c";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setRequestInterceptor(new JSONRequestInterceptor() {
            @Override
            public BoxJSONResponse onJSONRequest(BoxJSONRequest request, JsonObject body) {
                Assert.assertEquals("https://api.box.com/2.0/retention_policy_assignments",
                        request.getUrl().toString());
                Assert.assertEquals("POST", request.getMethod());

                JsonArray filterFields = body.get("filter_fields").asArray();
                Assert.assertEquals("9b80ed1d-df05-41db-840a-38fc77c5fe0c",
                        filterFields.get(0).asObject().get("field").asString());
                Assert.assertEquals("f67f1029-4c38-43f7-8daf-86db2bf601ce",
                        filterFields.get(0).asObject().get("value").asString());
                return new BoxJSONResponse() {
                    @Override
                    public String getJSON() {
                        return "{\"id\": \"0\", \"type\": \"retention_policy_assignment\"}";
                    }
                };
            }
        });


        MetadataFieldFilter filter1 = new MetadataFieldFilter("9b80ed1d-df05-41db-840a-38fc77c5fe0c",
                "f67f1029-4c38-43f7-8daf-86db2bf601ce");
        BoxRetentionPolicyAssignment.Info info = BoxRetentionPolicyAssignment.createAssignmentToMetadata(api, policyID,
                templateID, filter1);
    }
}
