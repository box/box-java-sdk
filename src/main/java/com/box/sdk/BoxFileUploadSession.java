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

/**
 * This API provides a way to reliably upload larger files to Box by chunking them into a sequence of parts.
 * When using this APIinstead of the single file upload API, a request failure means a client only needs to
 * retry upload of a single part instead of the entire file.  Parts can also be uploaded in parallel allowing
 * for potential performance improvement.
 */
@BoxResourceType("upload_session")
public class BoxFileUploadSession extends BoxResource {

    private static final String DIGEST_HEADER_PREFIX_SHA = "sha=";
    private static final String DIGEST_ALGORITHM_SHA1 = "SHA1";

    private static final String OFFSET_QUERY_STRING = "offset";
    private static final String LIMIT_QUERY_STRING = "limit";

    private Info sessionInfo;

    /**
     * Constructs a BoxFileUploadSession for a file with a given ID.
     * @param  api the API connection to be used by the upload session.
     * @param  id  the ID of the upload session.
     */
    BoxFileUploadSession(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Model contains the upload session information.
     */
    public class Info extends BoxResource.Info {

        private Date sessionExpiresAt;
        private String uploadSessionId;
        private Endpoints sessionEndpoints;
        private int partSize;
        private int totalParts;
        private int partsProcessed;

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
            BoxFileUploadSession.this.sessionInfo = this;
        }

        /**
         * Returns the BoxFileUploadSession isntance to which this object belongs to.
         * @return the instance of upload session.
         */
        public BoxFileUploadSession getResource() {
            return BoxFileUploadSession.this;
        }

        /**
         * Returns the total parts of the file that is uploaded in the upload session.
         * @return the total number of parts.
         */
        public int getTotalParts() {
            return this.totalParts;
        }

        /**
         * Returns the parts that are processed so for.
         * @return the number of the processed parts.
         */
        public int getPartsProcessed() {
            return this.partsProcessed;
        }

        /**
         * Returns the date and time at which the upload session expires.
         * @return the date and time in UTC format.
         */
        public Date getSessionExpiresAt() {
            return this.sessionExpiresAt;
        }

        /**
         * Returns the upload session id.
         * @return the id string.
         */
        public String getUploadSessionId() {
            return this.uploadSessionId;
        }

        /**
         * Returns the session endpoints that can be called for this upload session.
         * @return the Endpoints instance.
         */
        public Endpoints getSessionEndpoints() {
            return this.sessionEndpoints;
        }

        /**
         * Returns the size of the each part. Only the last part of the file can be lessor than this value.
         * @return the part size.
         */
        public int getPartSize() {
            return this.partSize;
        }

        @Override
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
            } else if (memberName.equals("id")) {
                this.uploadSessionId = value.asString();
            } else if (memberName.equals("part_size")) {
                this.partSize = Integer.valueOf(value.toString());
            } else if (memberName.equals("session_endpoints")) {
                this.sessionEndpoints = new Endpoints(value.asObject());
            } else if (memberName.equals("total_parts")) {
                this.totalParts = value.asInt();
            } else if (memberName.equals("num_parts_processed")) {
                this.partsProcessed = value.asInt();
            }
        }
    }

    /**
     * Represents the end points specific to an upload session.
     */
    public class Endpoints extends BoxJSONObject {
        private URL listPartsEndpoint;
        private URL commitEndpoint;
        private URL uploadPartEndpoint;
        private URL statusEndpoint;
        private URL abortEndpoint;

        /**
         * Constructs an Endpoints object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Endpoints(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Returns the list parts end point.
         * @return the url of the list parts end point.
         */
        public URL getListPartsEndpoint() {
            return this.listPartsEndpoint;
        }

        /**
         * Returns the commit end point.
         * @return the url of the commit end point.
         */
        public URL getCommitEndpoint() {
            return this.commitEndpoint;
        }

        /**
         * Returns the upload part end point.
         * @return the url of the upload part end point.
         */
        public URL getUploadPartEndpoint() {
            return this.uploadPartEndpoint;
        }

        /**
         * Returns the upload session status end point.
         * @return the url of the session end point.
         */
        public URL getStatusEndpoint() {
            return this.statusEndpoint;
        }

        /**
         * Returns the abort upload session end point.
         * @return the url of the abort end point.
         */
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

    /**
     * Uploads chunk of a stream to an open upload session.
     * @param stream the stream that is used to read the chunck using the offset and part size.
     * @param offset the byte position where the chunk begins in the file.
     * @param partSize the part size returned as part of the upload session instance creation.
     *                 Only the last chunk can have a lesser value.
     * @param totalSizeOfFile The total size of the file being uploaded.
     * @return the part instance that contains the part id, offset and part size.
     */
    public BoxFileUploadSessionPart uploadPart(InputStream stream, long offset, int partSize,
                                               long totalSizeOfFile) {

        URL uploadPartURL = this.sessionInfo.getSessionEndpoints().getUploadPartEndpoint();

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), uploadPartURL, HttpMethod.PUT);
        request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM);

        //Read the partSize bytes from the stream
        byte[] bytes = new byte[partSize];
        try {
            stream.read(bytes);
        } catch (IOException ioe) {
            throw new BoxAPIException("Reading data from stream failed.", ioe);
        }

        return this.uploadPart(bytes, offset, partSize, totalSizeOfFile);
    }

    /**
     * Uploads bytes to an open upload session.
     * @param data data
     * @param offset the byte position where the chunk begins in the file.
     * @param partSize the part size returned as part of the upload session instance creation.
     *                 Only the last chunk can have a lesser value.
     * @param totalSizeOfFile The total size of the file being uploaded.
     * @return the part instance that contains the part id, offset and part size.
     */
    public BoxFileUploadSessionPart uploadPart(byte[] data, long offset, int partSize,
                                               long totalSizeOfFile) {
        URL uploadPartURL = this.sessionInfo.getSessionEndpoints().getUploadPartEndpoint();

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), uploadPartURL, HttpMethod.PUT);
        request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM);

        MessageDigest digestInstance = null;
        try {
            digestInstance = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException ae) {
            throw new BoxAPIException("Digest algorithm not found", ae);
        }

        //Creates the digest using SHA1 algorithm. Then encodes the bytes using Base64.
        byte[] digestBytes = digestInstance.digest(data);
        String digest = Base64.encode(digestBytes);
        request.addHeader(HttpHeaders.DIGEST, DIGEST_HEADER_PREFIX_SHA + digest);
        //Content-Range: bytes offset-part/totalSize
        request.addHeader(HttpHeaders.CONTENT_RANGE,
                "bytes " + offset + "-" + (offset + partSize - 1) + "/" + totalSizeOfFile);

        //Creates the body
        request.setBody(new ByteArrayInputStream(data));
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        BoxFileUploadSessionPart part = new BoxFileUploadSessionPart((JsonObject) jsonObject.get("part"));
        return part;
    }

    /**
     * Returns a list of all parts that have been uploaded to an upload session.
     * @param offset paging marker for the list of parts.
     * @param limit maximum number of parts to return.
     * @return the list of parts.
     */
    public BoxFileUploadSessionPartList listParts(int offset, int limit) {
        URL listPartsURL = this.sessionInfo.getSessionEndpoints().getListPartsEndpoint();
        URLTemplate template = new URLTemplate(listPartsURL.toString());

        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam(OFFSET_QUERY_STRING, offset);
        String queryString = builder.appendParam(LIMIT_QUERY_STRING, limit).toString();

        //Template is initalized with the full URL. So empty string for the path.
        URL url = template.buildWithQuery("", queryString);

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        return new BoxFileUploadSessionPartList(jsonObject);
    }

    /**
     * Commit an upload session after all parts have been uploaded, creating the new file or the version.
     * @param digest the base64-encoded SHA-1 hash of the file being uploaded.
     * @param parts the list of uploaded parts to be committed.
     * @param attributes the key value pairs of attributes from the file instance.
     * @param ifMatch ensures that your app only alters files/folders on Box if you have the current version.
     * @param ifNoneMatch ensure that it retrieve unnecessary data if the most current version of file is on-hand.
     * @return the created file instance.
     */
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

        //Creates the body of the request
        String body = this.getCommitBody(parts, attributes);
        request.setBody(body);

        BoxAPIResponse response = request.send();
        //Retry the commit operation after the given number of seconds if the HTTP response code is 202.
        if (response.getResponseCode() == 202) {
            String retryInterval = response.getHeaderField("retry-after");
            if (retryInterval != null) {
                try {
                    Thread.sleep(new Integer(retryInterval) * 1000);
                } catch (InterruptedException ie) {
                    throw new BoxAPIException("Commit retry failed. ", ie);
                }

                return this.commit(digest, parts, attributes, ifMatch, ifNoneMatch);
            }
        }

        if (response instanceof BoxJSONResponse) {
            //Create the file instance from the response
            return this.getFile((BoxJSONResponse) response);
        } else {
            throw new BoxAPIException("Commit response content type is not application/json. The response code : "
                    + response.getResponseCode());
        }
    }

    /*
     * Creates the file isntance from the JSON body of the response.
     */
    private BoxFile.Info getFile(BoxJSONResponse response) {
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        JsonArray array = (JsonArray) jsonObject.get("entries");
        JsonObject fileObj = (JsonObject) array.get(0);

        BoxFile file = new BoxFile(this.getAPI(), fileObj.get("id").asString());

        return file.new Info(fileObj);
    }

    /*
     * Creates the JSON body for the commit request.
     */
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

    /**
     * Get the status of the upload session. It contains the number of parts that are processed so far,
     * the total number of parts required for the commit and expiration date and time of the upload session.
     * @return the status.
     */
    public BoxFileUploadSession.Info getStatus() {
        URL statusURL = this.sessionInfo.getSessionEndpoints().getStatusEndpoint();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), statusURL, HttpMethod.GET);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        this.sessionInfo.update(jsonObject);

        return this.sessionInfo;
    }

    /**
     * Abort an upload session, discarding any chunks that were uploaded to it.
     */
    public void abort() {
        URL abortURL = this.sessionInfo.getSessionEndpoints().getAbortEndpoint();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), abortURL, HttpMethod.DELETE);
        request.send();
    }
}
