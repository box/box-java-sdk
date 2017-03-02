package com.box.sdk;

import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;

/**
 *
 */
public final class LargeFileUpload {

    private static final String DIGEST_HEADER_PREFIX_SHA = "sha=";
    private static final String DIGEST_ALGORITHM_SHA1 = "SHA1";

    private static final String MARKER_QUERY_STRING = "marker";
    private static final String LIMIT_QUERY_STRING = "limit";

    private LargeFileUpload() {
    }

    static BoxFile.Info upload(BoxAPIConnection boxApi, String folderId, InputStream stream, URL url,
                               String fileName, long fileSize) throws Exception {

        BoxFileUploadSession.Info session = createUploadSession(boxApi, folderId, url, fileName, fileSize);

        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        List<BoxFileUploadSessionPart> parts = uploadParts(session, stream, fileSize, digest);

        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        BoxFileUploadSessionPartList list = session.getResource().listParts(0, 1000);
        try {
            return session.getResource().commit(digestStr, parts, null, null, null);
        } finally {
            session.getResource().abortUploadSession();
        }
    }

    private static BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, String folderId,
                                                         URL url, String fileName, long fileSize) {

        BoxJSONRequest request = new BoxJSONRequest(boxApi, url, HttpMethod.POST);

        JsonObject body = new JsonObject();
        body.add("folder_id", folderId);
        body.add("file_name", fileName);
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        String sessionId = jsonObject.get("upload_session_id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(boxApi, sessionId);

        return session.new Info(jsonObject);
    }

    static BoxFile.Info upload(BoxAPIConnection boxApi, InputStream stream, URL url, long fileSize) throws Exception {
        BoxFileUploadSession.Info session = createUploadSession(boxApi, url, fileSize);

        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        List<BoxFileUploadSessionPart> parts = uploadParts(session, stream, fileSize, digest);

        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        BoxFileUploadSessionPartList list = session.getResource().listParts(0, 1000);
        try {
            return session.getResource().commit(digestStr, parts, null, null, null);
        } finally {
            session.getResource().abortUploadSession();
        }
    }

    private static BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, URL url, long fileSize) {
        BoxJSONRequest request = new BoxJSONRequest(boxApi, url, HttpMethod.POST);
        JsonObject body = new JsonObject();
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        String sessionId = jsonObject.get("upload_session_id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(boxApi, sessionId);

        return session.new Info(jsonObject);
    }

    private static List<BoxFileUploadSessionPart> uploadParts(BoxFileUploadSession.Info session, InputStream stream,
                                                              long fileSize, MessageDigest digest) throws Exception {

        DigestInputStream dis = new DigestInputStream(stream, digest);
        List<BoxFileUploadSessionPart> parts = new ArrayList<BoxFileUploadSessionPart>();

        long partSize = session.getPartSize();
        long offset = 0;
        long processed = 0;
        while (processed < fileSize) {
            long diff = fileSize - processed;
            if (diff < partSize) {
                partSize = diff;
            }

            BoxFileUploadSessionPart part = uploadPart(session.getResource(), dis, offset, partSize, fileSize);
            parts.add(part);

            processed += partSize;
            offset += partSize;
        }

        return parts;
    }

    private static BoxFileUploadSessionPart uploadPart(BoxFileUploadSession session, InputStream stream, long offset,
                                   long partSize, long fileSize) throws Exception {

        String partId = generateHex();

        for (int i = 0; i < 3; i++) {
            try {
                return session.uploadPart(partId, stream, offset, partSize, fileSize);
            } catch (BoxAPIException ex) {
                if (i == 2) {
                    throw ex;
                }
            }
        }

        return null;
    }

    public static String generateHex() {
        String hex = "";
        while (hex.length() != 8) {
            Random random = new Random();
            int val = random.nextInt();
            hex = Integer.toHexString(val);
        }

        return hex;
    }
}
