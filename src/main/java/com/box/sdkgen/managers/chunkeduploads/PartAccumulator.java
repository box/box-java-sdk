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

  public PartAccumulator(
      long lastIndex, List<UploadPart> parts, long fileSize, String uploadPartUrl, Hash fileHash) {
    this.lastIndex = lastIndex;
    this.parts = parts;
    this.fileSize = fileSize;
    this.uploadPartUrl = uploadPartUrl;
    this.fileHash = fileHash;
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
}
