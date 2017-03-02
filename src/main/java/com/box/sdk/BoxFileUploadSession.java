package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.box.sdk.http.ContentType;
import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

@BoxResourceType("upload-session")
public class BoxFileUploadSession extends BoxResource {

    private static final String DIGEST_HEADER_PREFIX_SHA = "sha=";
    private static final String DIGEST_ALGORITHM_SHA1 = "SHA1";

    private static final String MARKER_QUERY_STRING = "marker";
    private static final String LIMIT_QUERY_STRING = "limit";

    private Info sessionInfo;

    BoxFileUploadSession(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public class Info extends BoxResource.Info {
        private BoxAPIConnection api;
        private Date sessionExpiresAt;
        private String uploadSessionId;
        private Endpoints sessionEndpoints;
        private long partSize;
        private int totalParts;
        private int partsProcessed;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
            BoxFileUploadSession.this.sessionInfo = this;
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
            BoxFileUploadSession.this.sessionInfo = this;
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
            BoxFileUploadSession.this.sessionInfo = this;
        }

        private BoxAPIConnection getAPI() {
            return this.api;
        }

        private void setAPI(BoxAPIConnection api) {
            this.api = api;
        }

        public BoxFileUploadSession getResource() {
            return BoxFileUploadSession.this;
        }

        public int getTotalParts() {
            return this.totalParts;
        }

        public int getPartsProcessed() {
            return this.partsProcessed;
        }

        public Date getSessionExpiresAt() {
            return this.sessionExpiresAt;
        }

        public String getUploadSessionId() {
            return this.uploadSessionId;
        }

        public Endpoints getSessionEndpoints() {
            return this.sessionEndpoints;
        }

        public long getPartSize() {
            return this.partSize;
        }

        protected void parseJSONMember(JsonObject.Member member) {

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("session_expires_at")) {
                try {
                    String dateStr = value.asString();
                    this.sessionExpiresAt = BoxDateFormat.parse(dateStr.substring(0, dateStr.length() - 1) + "-00:00");
                } catch (ParseException pe) {
                    assert false : "A ParseException indicates a bug in the SDK.";
                }
            } else if (memberName.equals("upload_session_id")) {
                this.uploadSessionId = value.asString();
            } else if (memberName.equals("part_size")) {
                this.partSize = Double.valueOf(value.toString()).longValue();
            } else if (memberName.equals("session_endpoints")) {
                this.sessionEndpoints = new Endpoints(value.asObject());
            } else if (memberName.equals("total_parts")) {
                this.totalParts = value.asInt();
            } else if (memberName.equals("num_parts_processed")) {
                this.partsProcessed = value.asInt();
            }
        }
    }

    public class Endpoints extends BoxJSONObject {
        private URL listPartsEndpoint;
        private URL commitEndpoint;
        private URL uploadPartEndpoint;
        private URL statusEndpoint;
        private URL abortEndpoint;

        Endpoints(JsonObject jsonObject) {
            super(jsonObject);
        }

        public URL getListPartsEndpoint() {
            return this.listPartsEndpoint;
        }

        public URL getCommitEndpoint() {
            return this.commitEndpoint;
        }

        public URL getUploadPartEndpoint() {
            return this.uploadPartEndpoint;
        }

        public URL getStatusEndpoint() {
            return this.statusEndpoint;
        }

        public URL getAbortEndpoint() {
            return this.abortEndpoint;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("list_parts")) {
                    this.listPartsEndpoint = new URL(value.asString());
                } else if (memberName.equals("commit")) {
                    this.commitEndpoint = new URL(value.asString());
                } else if (memberName.equals("upload_part")) {
                    this.uploadPartEndpoint = new URL(value.asString());
                } else if (memberName.equals("status")) {
                    this.statusEndpoint = new URL(value.asString());
                } else if (memberName.equals("abort")) {
                    this.abortEndpoint = new URL(value.asString());
                }
            } catch (MalformedURLException mue) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    public void uploadPart(String partId, InputStream stream, long offset, long partSize, long totalSizeOfFile)
            throws IOException, NoSuchAlgorithmException {

        URL uploadPartURL = this.sessionInfo.getSessionEndpoints().getUploadPartEndpoint();

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), uploadPartURL, HttpMethod.PUT);
        request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM);
        request.addHeader(HttpHeaders.X_BOX_PART_ID, partId);

        byte[] bytes = new byte[(int) partSize];
        stream.read(bytes);

        byte[] digestBytes = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1).digest(bytes);
        String digest = Base64.encode(digestBytes);
        request.addHeader(HttpHeaders.DIGEST, DIGEST_HEADER_PREFIX_SHA + digest);
        request.addHeader(HttpHeaders.CONTENT_RANGE,
                "bytes " + offset + "-" + (offset + partSize - 1) + "/" + totalSizeOfFile);

        request.setBody(new ByteArrayInputStream(bytes));
        request.send();
    }

    public BoxFileUploadSessionPartList listParts(int marker, int limit) {
        URL listPartsURL = this.sessionInfo.getSessionEndpoints().getListPartsEndpoint();
        URLTemplate template = new URLTemplate(listPartsURL.toString());

        String queryString = new QueryStringBuilder()
                .appendParam(MARKER_QUERY_STRING, marker)
                .appendParam(LIMIT_QUERY_STRING, limit)
                .toString();
        URL url = template.buildWithQuery("", queryString);

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        return new BoxFileUploadSessionPartList(jsonObject);
    }

    public BoxFile.Info commit(String digest, List<BoxFileUploadSessionPart> parts,
                      Map<String, String> attributes, String ifMatch, String ifNoneMatch) {

        URL commitURL = this.sessionInfo.getSessionEndpoints().getCommitEndpoint();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), commitURL, HttpMethod.POST);
        request.addHeader(HttpHeaders.DIGEST, DIGEST_HEADER_PREFIX_SHA + digest);
        request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON);

        if (ifMatch != null) {
            request.addHeader(HttpHeaders.IF_MATCH, ifMatch);
        }

        if (ifNoneMatch != null) {
            request.addHeader(HttpHeaders.IF_NONE_MATCH, ifNoneMatch);
        }

        String body = this.getCommitBody(parts, attributes);
        request.setBody(body);

        BoxAPIResponse response = request.send();
        if (response instanceof BoxJSONResponse) {
            return this.getFile((BoxJSONResponse) response);
        } else {
            throw new BoxAPIException("Commit response content type is not application/json. The response code : "
                    + response.getResponseCode());
        }
    }

    private BoxFile.Info getFile(BoxJSONResponse response) {
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        System.out.println("Response: " + response.getResponseCode() + "   : " + response.getJSON());

        JsonArray array = (JsonArray) jsonObject.get("entries");
        JsonObject fileObj = (JsonObject) array.get(0);

        BoxFile file = new BoxFile(this.getAPI(), fileObj.get("id").asString());

        return file.new Info(fileObj);
    }

    private String getCommitBody(List<BoxFileUploadSessionPart> parts, Map<String, String> attributes) {
        JsonObject jsonObject = new JsonObject();

        JsonArray array = new JsonArray();
        for (BoxFileUploadSessionPart part: parts) {
            JsonObject partObj = new JsonObject();
            partObj.add("part_id", part.getPartId());
            partObj.add("offset", part.getOffset());
            partObj.add("size", part.getSize());

            array.add(partObj);
        }
        jsonObject.add("parts", array);

        if (attributes != null) {
            JsonObject attrObj = new JsonObject();
            for (String key: attributes.keySet()) {
                attrObj.add(key, attributes.get(key));
            }
            jsonObject.add("attributes", attrObj);
        }

        return jsonObject.toString();
    }

    public BoxFileUploadSession.Info getUploadSessionStatus() {
        URL statusURL = this.sessionInfo.getSessionEndpoints().getStatusEndpoint();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), statusURL, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        this.sessionInfo.update(jsonObject);

        return this.sessionInfo;
    }

    public void abortUploadSession() {
        URL abortURL = this.sessionInfo.getSessionEndpoints().getAbortEndpoint();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), abortURL, HttpMethod.DELETE);
        request.send();
    }
}
