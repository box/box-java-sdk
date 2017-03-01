package com.box.sdk;

import com.box.sdk.*;
import com.box.sdk.http.ContentType;
import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

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

        MessageDigest digest = uploadParts(session, stream, fileSize);
        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        BoxFileUploadSessionPartList list = session.getResource().listParts(0, 1000);
        try {
            return session.getResource().commit(digestStr, list.getParts(), null, null, null);
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

        MessageDigest digest = uploadParts(session, stream, fileSize);
        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        BoxFileUploadSessionPartList list = session.getResource().listParts(0, 1000);
        try {
            return session.getResource().commit(digestStr, list.getParts(), null, null, null);
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

    private static MessageDigest uploadParts(BoxFileUploadSession.Info session, InputStream stream, long fileSize)
            throws Exception {

        MessageDigest digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        DigestInputStream dis = new DigestInputStream(stream, digest);

        long partSize = session.getPartSize();
        long offset = 0;
        long processed = 0;
        while (processed < fileSize) {
            long diff = fileSize - processed;
            if (diff < partSize) {
                partSize = diff;
            }

            uploadPart(session.getResource(), dis, offset, partSize, fileSize);

            processed += partSize;
            offset += partSize;
        }

        return digest;
    }

    private static void uploadPart(BoxFileUploadSession session, InputStream stream, long offset,
                                   long partSize, long fileSize) throws Exception {

        String partId = generateHex();

        for(int i = 0; i < 3; i++) {
            try {
                session.uploadPart(partId, stream, offset, partSize, fileSize);
                break;
            } catch (BoxAPIException ex) {
                if (i == 2) {
                    throw ex;
                }
            }
        }
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
