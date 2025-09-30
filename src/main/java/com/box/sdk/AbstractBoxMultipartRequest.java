package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Base class for multipart uploads
 *
 * <p>This class partially implements the HTTP multipart standard in order to upload files to Box.
 * The body of this * request type cannot be set directly. Instead, it can be modified by adding
 * multipart fields and setting file * contents. The body of multipart requests will not be logged
 * since they are likely to contain binary data.
 */
abstract class AbstractBoxMultipartRequest extends BoxAPIRequest {
  protected static final String BOUNDARY = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
  static final int BUFFER_SIZE = 8192;
  private final StringBuilder loggedRequest = new StringBuilder();
  private final Map<String, String> fields = new HashMap<>();
  private InputStream inputStream;
  private String filename;
  private long fileSize = -1;
  private UploadFileCallback callback;

  AbstractBoxMultipartRequest(BoxAPIConnection api, URL url) {
    super(api, url, "POST");
    this.addHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
  }

  /**
   * Sets the file contents of this request.
   *
   * @param inputStream a stream containing the file contents.
   * @param filename the name of the file.
   */
  public void setFile(InputStream inputStream, String filename) {
    this.inputStream = inputStream;
    this.filename = filename;
  }

  /**
   * Sets the file contents of this request.
   *
   * @param inputStream a stream containing the file contents.
   * @param filename the name of the file.
   * @param fileSize the size of the file. If the file content is coming from the dynamic stream,
   *     and it's full size cannot be determined on starting upload use fizeSize=-1 or use {@link
   *     AbstractBoxMultipartRequest#setFile(InputStream, String)}
   */
  public void setFile(InputStream inputStream, String filename, long fileSize) {
    this.setFile(inputStream, filename);
    this.fileSize = fileSize;
  }

  /**
   * Adds or updates a multipart field in this request.
   *
   * @param key the field's key.
   * @param value the field's value.
   */
  public void putField(String key, String value) {
    this.fields.put(key, value);
  }

  /**
   * Adds or updates a multipart field in this request.
   *
   * @param key the field's key.
   * @param value the field's value.
   */
  public void putField(String key, Date value) {
    this.fields.put(key, BoxDateFormat.format(value));
  }

  /**
   * Sets the callback which allows file content to be written on output stream.
   *
   * @param callback the callback which allows file content to be written on output stream.
   * @param filename the size of the file.
   */
  public void setUploadFileCallback(UploadFileCallback callback, String filename) {
    this.callback = callback;
    this.filename = filename;
  }

  /**
   * Sets the SHA1 hash of the file contents of this request. If set, it will ensure that the file
   * is not corrupted in transit.
   *
   * @param sha1 a string containing the SHA1 hash of the file contents.
   */
  public void setContentSHA1(String sha1) {
    this.addHeader("Content-MD5", sha1);
  }

  /**
   * This method is unsupported in BoxMultipartRequest. Instead, the body should be modified via the
   * {@code putField} and {@code setFile} methods.
   *
   * @param stream N/A
   * @throws UnsupportedOperationException this method is unsupported.
   */
  @Override
  public void setBody(InputStream stream) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method is unsupported in BoxMultipartRequest. Instead, the body should be modified via the
   * {@code putField} and {@code setFile} methods.
   *
   * @param body N/A
   * @throws UnsupportedOperationException this method is unsupported.
   */
  @Override
  public void setBody(String body) {
    throw new UnsupportedOperationException();
  }

  protected abstract String getPartName();

  protected abstract MediaType getPartContentType(String filename);

  @Override
  protected void resetBody() throws IOException {
    this.inputStream.reset();
    this.loggedRequest.setLength(0);
  }

  @Override
  protected String bodyToString() {
    return this.loggedRequest.toString();
  }

  @Override
  protected void writeMethodWithBody(
      Request.Builder requestBuilder, ProgressListener progressListener) {
    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    // Attributes should go first
    this.fields.forEach(bodyBuilder::addFormDataPart);
    // File should go second
    bodyBuilder.addFormDataPart(getPartName(), filename, getBody(progressListener));
    requestBuilder.post(bodyBuilder.build());
  }

  private RequestBody getBody(ProgressListener progressListener) {
    if (this.callback == null) {
      return new RequestBodyFromStream(
          this.inputStream, getPartContentType(filename), progressListener, fileSize);
    } else {
      return new RequestBodyFromCallback(this.callback, getPartContentType(filename));
    }
  }
}
