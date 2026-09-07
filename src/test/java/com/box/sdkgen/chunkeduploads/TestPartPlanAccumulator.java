package com.box.sdkgen.chunkeduploads;

import com.box.sdkgen.schemas.uploadpartplan.UploadPartPlan;
import java.util.List;

public class TestPartPlanAccumulator {

  public final int lastIndex;

  public final List<UploadPartPlan> parts;

  public final long fileSize;

  public TestPartPlanAccumulator(int lastIndex, List<UploadPartPlan> parts, long fileSize) {
    this.lastIndex = lastIndex;
    this.parts = parts;
    this.fileSize = fileSize;
  }

  public int getLastIndex() {
    return lastIndex;
  }

  public List<UploadPartPlan> getParts() {
    return parts;
  }

  public long getFileSize() {
    return fileSize;
  }
}
