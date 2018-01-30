package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;

/**
 * Utility class for uploading large files.
 */
public final class LargeFileUpload {

    private static final String DIGEST_HEADER_PREFIX_SHA = "sha=";
    private static final String DIGEST_ALGORITHM_SHA1 = "SHA1";

    private static final String OFFSET_QUERY_STRING = "offset";
    private static final String LIMIT_QUERY_STRING = "limit";
    private static final int DEFAULT_CONNECTIONS = 3;
    private static final int DEFAULT_TIMEOUT = 1;
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.HOURS;
    private static final int THREAD_POOL_WAIT_TIME_IN_MILLIS = 1000;
    private ThreadPoolExecutor executorService;
    private long timeout;
    private TimeUnit timeUnit;
    private int connections;

    /**
     * Creates a LargeFileUpload object.
     * @param nParallelConnections number of parallel http connections to use
     * @param timeOut time to wait before killing the job
     * @param unit time unit for the time wait value
     */
    public LargeFileUpload(int nParallelConnections, long timeOut, TimeUnit unit) {
        this.executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(nParallelConnections);
        this.timeout = timeOut;
        this.timeUnit = unit;
    }

    /**
     * Creates a LargeFileUpload object with a default number of parallel conections and timeout.
     */
    public LargeFileUpload() {
        this.executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(LargeFileUpload.DEFAULT_CONNECTIONS);
        this.timeout = LargeFileUpload.DEFAULT_TIMEOUT;
        this.timeUnit = LargeFileUpload.DEFAULT_TIMEUNIT;
    }

    private BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, String folderId,
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

        String sessionId = jsonObject.get("id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(boxApi, sessionId);

        return session.new Info(jsonObject);
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
     * @throws InterruptedException when a thread gets interupted.
     * @throws IOException when reading a stream throws exception.
     */
    public BoxFile.Info upload(BoxAPIConnection boxApi, String folderId, InputStream stream, URL url,
                               String fileName, long fileSize) throws InterruptedException, IOException {
        //Create a upload session
        BoxFileUploadSession.Info session = this.createUploadSession(boxApi, folderId, url, fileName, fileSize);
        return this.uploadHelper(session, stream, fileSize);
    }

    /**
     * Creates a new version of a large file.
     * @param boxApi the API connection to be used by the upload session.
     * @param stream the input stream that feeds the content of the file.
     * @param url the upload session URL.
     * @param fileSize the total size of the file.
     * @return the file instance that also contains the version information.
     * @throws InterruptedException when a thread gets interupted.
     * @throws IOException when reading a stream throws exception.
     */
    public BoxFile.Info upload(BoxAPIConnection boxApi, InputStream stream, URL url, long fileSize)
        throws InterruptedException, IOException {
        //creates a upload session
        BoxFileUploadSession.Info session = this.createUploadSession(boxApi, url, fileSize);
        return this.uploadHelper(session, stream, fileSize);
    }

    private BoxFile.Info uploadHelper(BoxFileUploadSession.Info session, InputStream stream, long fileSize)
        throws InterruptedException, IOException {
        //Upload parts using the upload session
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(DIGEST_ALGORITHM_SHA1);
        } catch (NoSuchAlgorithmException ae) {
            throw new BoxAPIException("Digest algorithm not found", ae);
        }
        DigestInputStream dis = new DigestInputStream(stream, digest);
        List<BoxFileUploadSessionPart> parts = this.uploadParts(session, dis, fileSize);

        //Creates the file hash
        byte[] digestBytes = digest.digest();
        String digestStr = Base64.encode(digestBytes);

        //Commit the upload session. If there is a failure, abort the commit.
        try {
            return session.getResource().commit(digestStr, parts, null, null, null);
        } catch (Exception e) {
            session.getResource().abort();
            throw new BoxAPIException("Unable to commit the upload session", e);
        }
    }

    private BoxFileUploadSession.Info createUploadSession(BoxAPIConnection boxApi, URL url, long fileSize) {
        BoxJSONRequest request = new BoxJSONRequest(boxApi, url, HttpMethod.POST);

        //Creates the body of the request
        JsonObject body = new JsonObject();
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        String sessionId = jsonObject.get("id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(boxApi, sessionId);

        return session.new Info(jsonObject);
    }

    /*
     * Upload parts of the file. The part size is retrieved from the upload session.
     */
    private List<BoxFileUploadSessionPart> uploadParts(BoxFileUploadSession.Info session, InputStream stream,
                                                       long fileSize) throws InterruptedException {
        List<BoxFileUploadSessionPart> parts = new ArrayList<BoxFileUploadSessionPart>();

        int partSize = session.getPartSize();
        long offset = 0;
        long processed = 0;
        int partPostion = 0;
        //Set the Max Queue Size to 1.5x the number of processors
        double maxQueueSizeDouble = Math.ceil(this.executorService.getMaximumPoolSize() * 1.5);
        int maxQueueSize = Double.valueOf(maxQueueSizeDouble).intValue();
        while (processed < fileSize) {
            //Waiting for any thread to finish before
            long timeoutForWaitingInMillis = TimeUnit.MILLISECONDS.convert(this.timeout, this.timeUnit);
            if (this.executorService.getCorePoolSize() <= this.executorService.getActiveCount()) {
                if (timeoutForWaitingInMillis > 0) {
                    Thread.sleep(LargeFileUpload.THREAD_POOL_WAIT_TIME_IN_MILLIS);
                    timeoutForWaitingInMillis -= THREAD_POOL_WAIT_TIME_IN_MILLIS;
                } else {
                    throw new BoxAPIException("Upload parts timedout");
                }
            }
            if (this.executorService.getQueue().size() < maxQueueSize) {
                long diff = fileSize - (long) processed;
                //The size last part of the file can be lesser than the part size.
                if (diff < (long) partSize) {
                    partSize = (int) diff;
                }
                parts.add(null);
                byte[] bytes = new byte[partSize];
                try {
                    int readStatus = stream.read(bytes);
                    if (readStatus == -1) {
                        throw new BoxAPIException("Stream ended while upload was progressing");
                    }
                } catch (IOException ioe) {
                    throw new BoxAPIException("Reading data from stream failed.", ioe);
                }
                this.executorService.execute(
                    new LargeFileUploadTask(session.getResource(), bytes, offset,
                        partSize, fileSize, parts, partPostion)
                );

                //Increase the offset and proceesed bytes to calculate the Content-Range header.
                processed += partSize;
                offset += partSize;
                partPostion++;
            }
        }
        this.executorService.shutdown();
        this.executorService.awaitTermination(this.timeout, this.timeUnit);
        return parts;
    }

    /**
     * Generates the Base64 encoded SHA-1 hash for content available in the stream.
     * It can be used to calculate the hash of a file.
     * @param stream the input stream of the file or data.
     * @return the Base64 encoded hash string.
     */
    public String generateDigest(InputStream stream) {
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
