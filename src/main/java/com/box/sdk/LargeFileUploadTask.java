package com.box.sdk;

import java.util.List;

/**
 *
 */
public class LargeFileUploadTask implements Runnable {

    private final int partPostion;
    private BoxFileUploadSession session;
    private byte[] data;
    private List<BoxFileUploadSessionPart> parts;
    private long offset;
    private int partSize;
    private long fileSize;

    /**
     * Runable task to create parallel http connctions for file upload session.
     * @param session       file upload session object
     * @param data          bytes to be uploaded
     * @param offset        stream offset
     * @param partSize      part size of the put request
     * @param fileSize      total file size
     * @param parts         list of the BoxFileUploadSessionPart objects
     * @param partPostion   sequence number of the part
     */
    public LargeFileUploadTask(BoxFileUploadSession session, byte[] data, long offset,
                               int partSize, long fileSize, List<BoxFileUploadSessionPart> parts, int partPostion) {
        this.session = session;
        this.data = data;
        this.offset = offset;
        this.partSize = partSize;
        this.fileSize = fileSize;
        this.parts = parts;
        this.partPostion = partPostion;
    }

    @Override
    public void run() {
        //Retries the upload part 3 times in case of failure.
        for (int i = 0; i < 3; i++) {
            try {
                BoxFileUploadSessionPart part =
                    this.session.uploadPart(this.data, this.offset, this.partSize, this.fileSize);
                this.parts.set(this.partPostion, part);
                return;
            } catch (BoxAPIException ex) {
                if (i == 2) {
                    throw ex;
                }
            }
        }
        throw new BoxAPIException("Upload part failed for offset: " + this.offset + " range: " + this.partSize);
    }
}
