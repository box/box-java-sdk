package com.box.sdk;

/** The listener interface for monitoring the progress of a long-running API call. */
public interface ProgressListener {

  /**
   * Invoked when the progress of the API call changes. In case of file uploads which are coming
   * from a dynamic stream the file size cannot be determined and total bytes will be reported as
   * -1.
   *
   * @param numBytes the number of bytes completed.
   * @param totalBytes the total number of bytes.
   */
  void onProgressChanged(long numBytes, long totalBytes);
}
