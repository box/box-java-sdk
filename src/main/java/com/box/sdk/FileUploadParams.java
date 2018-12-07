package com.box.sdk;

import java.io.InputStream;
import java.util.Date;

/**
 * Contains parameters for configuring an upload to Box.
 */
public class FileUploadParams {
    private InputStream content;
    private UploadFileCallback uploadFileCallback;
    private String name;
    private Date created;
    private Date modified;
    private long size;
    private ProgressListener listener;
    private String sha1;

    /**
     * Constructs a new FileUploadParams with default parameters.
     */
    public FileUploadParams() { }

    /**
     * Gets the content that will be uploaded to Box.
     * @return an InputStream that reads the content to be uploaded to Box.
     */
    public InputStream getContent() {
        return this.content;
    }

    /**
     * Sets the content that will be uploaded to Box.
     * @param  content an InputStream that reads from the content to be uploaded to Box.
     * @return         this FileUploadParams object for chaining.
     */
    public FileUploadParams setContent(InputStream content) {
        this.content = content;
        return this;
    }

    /**
     * @return content writer callback.
     */
    public UploadFileCallback getUploadFileCallback() {
        return this.uploadFileCallback;
    }

    /**
     * Sets the content writer callback.
     *
     * @param uploadFileCallback callback called when file upload starts.
     * @return         this FileUploadParams object for chaining.
     */
    public FileUploadParams setUploadFileCallback(UploadFileCallback uploadFileCallback) {
        this.uploadFileCallback = uploadFileCallback;
        return this;
    }

    /**
     * Gets the name that will be given to the uploaded file.
     * @return the name that will be given to the uploaded file.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name that will be given to the uploaded file.
     * @param  name the name that will be given to the uploaded file.
     * @return      this FileUploadParams object for chaining.
     */
    public FileUploadParams setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets the content created date that will be given to the uploaded file.
     * @return the content created date that will be given to the uploaded file.
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * Sets the content created date that will be given to the uploaded file.
     * @param  created the content created date that will be given to the uploaded file.
     * @return         this FileUploadParams object for chaining.
     */
    public FileUploadParams setCreated(Date created) {
        this.created = created;
        return this;
    }

    /**
     * Gets the content modified date that will be given to the uploaded file.
     * @return the content modified date that will be given to the uploaded file.
     */
    public Date getModified() {
        return this.modified;
    }

    /**
     * Sets the content modified date that will be given to the uploaded file.
     * @param  modified the content modified date that will be given to the uploaded file.
     * @return          this FileUploadParams object for chaining.
     */
    public FileUploadParams setModified(Date modified) {
        this.modified = modified;
        return this;
    }

    /**
     * Gets the size of the file's content used for monitoring the upload's progress.
     * @return the size of the file's content.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Sets the size of the file content used for monitoring the upload's progress.
     * @param  size the size of the file's content.
     * @return      this FileUploadParams object for chaining.
     */
    public FileUploadParams setSize(long size) {
        this.size = size;
        return this;
    }

    /**
     * Gets the ProgressListener that will be used for monitoring the upload's progress.
     * @return the ProgressListener that will be used for monitoring the upload's progress.
     */
    public ProgressListener getProgressListener() {
        return this.listener;
    }

    /**
     * Sets the ProgressListener that will be used for monitoring the upload's progress.
     * @param  listener the listener that will be used for monitoring the upload's progress.
     * @return          this FileUploadParams object for chaining.
     */
    public FileUploadParams setProgressListener(ProgressListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * Set the SHA-1 hash of the file to ensure it is not corrupted during the upload.
     * @param sha1 the SHA-1 hash of the file.
     * @return     this FileUploadParams for chaining.
     */
    public FileUploadParams setSHA1(String sha1) {
        this.sha1 = sha1;
        return this;
    }

    /**
     * Gets the file's SHA-1 hash.
     * @return the file hash.
     */
    public String getSHA1() {
        return this.sha1;
    }
}
