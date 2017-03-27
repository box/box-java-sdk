package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;

/**
 * Utility class for uploading large files.
 */
public final class LargeFileUpload {

    private static final String DIGEST_HEADER_PREFIX_SHA = "sha=";
    private static final String DIGEST_ALGORITHM_SHA1 = "SHA1";

    private static final String MARKER_QUERY_STRING = "marker";
    private static final String LIMIT_QUERY_STRING = "limit";

    private LargeFileUpload() {
    }

    /**
     * Uploads a new large file.
     * @param boxApi the API connection to be used by the upload session.
     * @param folderId the id of the folder in which the file will be uploaded.
     * @param stream the input stream that feeds the content of the file.
     * @param url the upload session URL.
     * @param fileName the name of the file to be created.
     * @param fileSize the total size of the file.
     * @return the created file instance.
     */
    static BoxFile.Info upload(BoxAPIConnection boxApi, String folderId, InputStream stream, URL url,
                               String fileName, long fileSize) {

        //Create a upload session
        BoxFileUploadSession.Info session = createUploadSession(boxApi, folderId, url, fileName, fileSize);

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException ae) {
            throw new BoxAPIException("Digest algorithm not found", ae);
        }

        //Upload parts using the upload session
        List<BoxFileUploadSessionPart> parts = uploadParts(session, stream, fileSize, digest);

        //Creates the file hash
        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        //Commit the upload session. If there is a failure, abort the commit.
        try {
            return session.getResource().commit(digestStr, parts, null, null, null);
        } finally {
            session.getResource().abort();
        }
    }

    private static BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, String folderId,
                                                         URL url, String fileName, long fileSize) {

        BoxJSONRequest request = new BoxJSONRequest(boxApi, url, HttpMethod.POST);

        //Create the JSON body of the request
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

    /**
     * Creates a new version of a large file.
     * @param boxApi the API connection to be used by the upload session.
     * @param stream the input stream that feeds the content of the file.
     * @param url the upload session URL.
     * @param fileSize the total size of the file.
     * @return the file instance that also contains the version information.
     */
    static BoxFile.Info upload(BoxAPIConnection boxApi, InputStream stream, URL url, long fileSize) {

        //creates a upload session
        BoxFileUploadSession.Info session = createUploadSession(boxApi, url, fileSize);

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException ae) {
            throw new BoxAPIException("Digest algorithm not found", ae);
        }

        //Upload parts using the upload session
        List<BoxFileUploadSessionPart> parts = uploadParts(session, stream, fileSize, digest);

        //Creates the file hash
        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        //Commit the upload session. If there is a failure, abort the commit.
        try {
            return session.getResource().commit(digestStr, parts, null, null, null);
        } finally {
            session.getResource().abort();
        }
    }

    private static BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, URL url, long fileSize) {
        BoxJSONRequest request = new BoxJSONRequest(boxApi, url, HttpMethod.POST);

        //Creates the body of the request
        JsonObject body = new JsonObject();
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        String sessionId = jsonObject.get("upload_session_id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(boxApi, sessionId);

        return session.new Info(jsonObject);
    }

    /*
     * Upload parts of the file. The part size is retrieved from the upload session.
     */
    private static List<BoxFileUploadSessionPart> uploadParts(BoxFileUploadSession.Info session, InputStream stream,
                                                              long fileSize, MessageDigest digest) {

        DigestInputStream dis = new DigestInputStream(stream, digest);
        List<BoxFileUploadSessionPart> parts = new ArrayList<BoxFileUploadSessionPart>();

        long partSize = session.getPartSize();
        long offset = 0;
        long processed = 0;
        while (processed < fileSize) {
            long diff = fileSize - processed;
            //The size last part of the file can be lesser than the part size.
            if (diff < partSize) {
                partSize = diff;
            }

            //Upload a part
            BoxFileUploadSessionPart part = uploadPart(session.getResource(), dis, offset, partSize, fileSize);
            parts.add(part);

            //Increase the offset and proceesed bytes to calculate the Content-Range header.
            processed += partSize;
            offset += partSize;
        }

        return parts;
    }

    /*
     * Uploads the part of the file.
     */
    private static BoxFileUploadSessionPart uploadPart(BoxFileUploadSession session, InputStream stream, long offset,
                                   long partSize, long fileSize) {

        String partId = generateHex();

        //Retries the upload part 3 times in case of failure.
        for (int i = 0; i < 3; i++) {
            try {
                return session.uploadPart(partId, stream, offset, partSize, fileSize);
            } catch (BoxAPIException ex) {
                if (i == 2) {
                    throw ex;
                }
            }
        }

        throw new BoxAPIException("Upload part failed for offset: " + offset + " range: " + partSize);
    }

    /**
     * Generates a 8 character random hex value.
     * @return the hex string.
     */
    public static String generateHex() {
        String hex = "";
        while (hex.length() != 8) {
            Random random = new Random();
            int val = random.nextInt();
            hex = Integer.toHexString(val);
        }

        return hex;
    }

    /**
     * Generates the Base64 encoded SHA-1 hash for content available in the stream.
     * It can be used to calculate the hash of a file.
     * @param stream the input stream of the file or data.
     * @return the Base64 encoded hash string.
     */
    public static String generateDigest(InputStream stream) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException ae) {
            throw new BoxAPIException("Digest algorithm not found", ae);
        }

        //Calcuate the digest using the stream.
        DigestInputStream dis = new DigestInputStream(stream, digest);
        try {
            int value = dis.read();
            while (value != -1) {
                value = dis.read();
            }
        } catch (IOException ioe) {
            throw new BoxAPIException("Reading the stream failed.", ioe);
        }

        //Get the calculated digest for the stream
        byte[] digestBytes = digest.digest();
        return Base64.encode(digestBytes);
    }
}
