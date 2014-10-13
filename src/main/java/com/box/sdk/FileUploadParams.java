package com.box.sdk;

import java.io.InputStream;
import java.util.Date;

public class FileUploadParams {
    private InputStream content;
    private String name;
    private Date created;
    private Date modified;
    private long size;
    private ProgressListener listener;

    public InputStream getContent() {
        return this.content;
    }

    public FileUploadParams setContent(InputStream content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public FileUploadParams setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreated() {
        return this.created;
    }

    public FileUploadParams setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Date getModified() {
        return this.modified;
    }

    public FileUploadParams setModified(Date modified) {
        this.modified = modified;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public FileUploadParams setSize(long size) {
        this.size = size;
        return this;
    }

    public ProgressListener getProgressListener() {
        return this.listener;
    }

    public FileUploadParams setProgressListener(ProgressListener listener) {
        this.listener = listener;
        return this;
    }
}
