package com.box.sdkgen.chunkeduploads;

import com.box.sdkgen.internal.utils.Hash;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import java.util.List;

public class TestPartAccumulator {

  public final int lastIndex;

  public final List<UploadPart> parts;

  public final long fileSize;

  public String uploadPartUrl;

  public String uploadSessionId;

  public final Hash fileHash;

  public TestPartAccumulator(int lastIndex, List<UploadPart> parts, long fileSize, Hash fileHash) {
    this.lastIndex = lastIndex;
    this.parts = parts;
    this.fileSize = fileSize;
    this.fileHash = fileHash;
    this.uploadPartUrl = "";
    this.uploadSessionId = "";
  }

  protected TestPartAccumulator(Builder builder) {
    this.lastIndex = builder.lastIndex;
    this.parts = builder.parts;
    this.fileSize = builder.fileSize;
    this.uploadPartUrl = builder.uploadPartUrl;
    this.uploadSessionId = builder.uploadSessionId;
    this.fileHash = builder.fileHash;
  }

  public int getLastIndex() {
    return lastIndex;
  }

  public List<UploadPart> getParts() {
    return parts;
  }

  public long getFileSize() {
    return fileSize;
  }

  public String getUploadPartUrl() {
    return uploadPartUrl;
  }

  public String getUploadSessionId() {
    return uploadSessionId;
  }

  public Hash getFileHash() {
    return fileHash;
  }

  public static class Builder {

    protected final int lastIndex;

    protected final List<UploadPart> parts;

    protected final long fileSize;

    protected String uploadPartUrl;

    protected String uploadSessionId;

    protected final Hash fileHash;

    public Builder(int lastIndex, List<UploadPart> parts, long fileSize, Hash fileHash) {
      this.lastIndex = lastIndex;
      this.parts = parts;
      this.fileSize = fileSize;
      this.fileHash = fileHash;
    }

    public Builder uploadPartUrl(String uploadPartUrl) {
      this.uploadPartUrl = uploadPartUrl;
      return this;
    }

    public Builder uploadSessionId(String uploadSessionId) {
      this.uploadSessionId = uploadSessionId;
      return this;
    }

    public TestPartAccumulator build() {
      if (this.uploadPartUrl == null) {
        this.uploadPartUrl = "";
      }
      if (this.uploadSessionId == null) {
        this.uploadSessionId = "";
      }
      return new TestPartAccumulator(this);
    }
  }
}
