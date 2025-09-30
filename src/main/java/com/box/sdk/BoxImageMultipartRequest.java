package com.box.sdk;

import java.net.URL;
import okhttp3.MediaType;

/**
 * Used to make HTTP multipart requests to the Box API to upload an image. You can define part name
 * that contains image binary data. Image type will be determined from the file name.
 *
 * <p>This class partially implements the HTTP multipart standard in order to upload files to Box.
 * The body of this request type cannot be set directly. Instead, it can be modified by adding
 * multipart fields and setting file contents. The body of multipart requests will not be logged
 * since they are likely to contain binary data.
 */
class BoxImageMultipartRequest extends AbstractBoxMultipartRequest {
  private final String partName;

  /**
   * Constructs an authenticated BoxImageMultipartRequest using a provided BoxAPIConnection. You can
   * define part name that contains image binary data. Image type will be determined from the file
   * name.
   *
   * @param api an API connection for authenticating the request.
   * @param url the URL of the request.
   * @param partName the name of the part that will contain image
   */
  BoxImageMultipartRequest(BoxAPIConnection api, URL url, String partName) {
    super(api, url);
    this.partName = partName;
  }

  @Override
  protected String getPartName() {
    return partName;
  }

  @Override
  protected MediaType getPartContentType(String filename) {
    return MediaType.parse(determineImageTypeFromFileName(filename));
  }

  private String determineImageTypeFromFileName(String filename) {
    String[] filenameAndExtension = filename.split("\\.");
    if (filenameAndExtension.length > 1) {
      return "image/" + filenameAndExtension[filenameAndExtension.length - 1];
    }
    throw new IllegalArgumentException(
        "Could not determine file extenstion for Avatar Upload. Filename: " + filename);
  }
}
