package com.box.sdkgen.managers.chunkeduploads;

import com.box.sdkgen.internal.utils.Hash;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import java.util.List;

public class PartAccumulator {

  public final long lastIndex;

  public final List<UploadPart> parts;

  public final long fileSize;

  public final String uploadPartUrl;

  public final Hash fileHash;

  public String planUrl;

  public PartAccumulator(
      long lastIndex, List<UploadPart> parts, long fileSize, String uploadPartUrl, Hash fileHash) {
    this.lastIndex = lastIndex;
    this.parts = parts;
    this.fileSize = fileSize;
    this.uploadPartUrl = uploadPartUrl;
    this.fileHash = fileHash;
    this.planUrl = "";
  }

  protected PartAccumulator(Builder builder) {
    this.lastIndex = builder.lastIndex;
    this.parts = builder.parts;
    this.fileSize = builder.fileSize;
    this.uploadPartUrl = builder.uploadPartUrl;
    this.fileHash = builder.fileHash;
    this.planUrl = builder.planUrl;
  }

  public long getLastIndex() {
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

  public Hash getFileHash() {
    return fileHash;
  }

  public String getPlanUrl() {
    return planUrl;
  }

  public static class Builder {

    protected final long lastIndex;

    protected final List<UploadPart> parts;

    protected final long fileSize;

    protected final String uploadPartUrl;

    protected final Hash fileHash;

    protected String planUrl;

    public Builder(
        long lastIndex,
        List<UploadPart> parts,
        long fileSize,
        String uploadPartUrl,
        Hash fileHash) {
      this.lastIndex = lastIndex;
      this.parts = parts;
      this.fileSize = fileSize;
      this.uploadPartUrl = uploadPartUrl;
      this.fileHash = fileHash;
    }

    public Builder planUrl(String planUrl) {
      this.planUrl = planUrl;
      return this;
    }

    public PartAccumulator build() {
      if (this.planUrl == null) {
        this.planUrl = "";
      }
      return new PartAccumulator(this);
    }
  }
}
