package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStream;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

/**
 * Provides methods to allow users to download multiple files and folders as a single zip file.
 * Users can download up to either 32GB or 10,000 files in one batch (whichever limitation is hit
 * first) as a single zip file.
 */
public class BoxZip {
  /** Zip URL Template. */
  public static final URLTemplate ZIP_URL_TEMPLATE = new URLTemplate("zip_downloads");

  private final BoxAPIConnection api;

  /**
   * Constructs a Zip to be used by everything.
   *
   * @param api the API connection to be used by the Zip.
   */
  public BoxZip(BoxAPIConnection api) {
    this.api = api;
  }

  /**
   * Creates a zip of multiple files and folders.
   *
   * @param name the name of the zip file to be created
   * @param items list of files or folders to be part of the created zip
   * @return information about the created zip file
   */
  public BoxZipInfo create(String name, List<BoxZipItem> items) {
    JsonArray itemsArray = new JsonArray();
    for (BoxZipItem item : items) {
      itemsArray.add(item.getJSONObject());
    }
    JsonObject requestJSON = new JsonObject();
    requestJSON.add("items", itemsArray);
    requestJSON.add("download_file_name", name);

    URL url = ZIP_URL_TEMPLATE.build(this.getAPI().getBaseURL());
    BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
    request.setBody(requestJSON.toString());
    try (BoxJSONResponse response = request.send()) {
      JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
      return new BoxZipInfo(responseJSON);
    }
  }

  /**
   * Creates a zip and downloads it to a given OutputStream.
   *
   * @param name the name of the zip file to be created
   * @param items list of files or folders to be part of the created zip
   * @param output the stream to where the zip file will be written.
   * @return information about status of the download
   */
  public BoxZipDownloadStatus download(String name, List<BoxZipItem> items, OutputStream output) {
    return this.download(name, items, output, null);
  }

  /**
   * Creates a zip and downloads its contents its to a given OutputStream.
   *
   * @param name the name of the zip file to be created
   * @param items list of files or folders to be part of the created zip
   * @param output the stream to where the zip file will be written.
   * @param listener a listener for monitoring the download's progress.
   * @return information about status of the download
   */
  public BoxZipDownloadStatus download(
      String name, List<BoxZipItem> items, OutputStream output, ProgressListener listener) {
    BoxZipInfo zipInfo = this.create(name, items);
    BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), zipInfo.getDownloadURL(), "GET");
    BoxAPIResponse response = request.send();
    writeStream(response, output, listener);
    BoxJSONRequest statusRequest = new BoxJSONRequest(this.getAPI(), zipInfo.getStatusURL(), "GET");
    try (BoxJSONResponse statusResponse = statusRequest.send()) {
      JsonObject statusResponseJSON = Json.parse(statusResponse.getJSON()).asObject();
      return new BoxZipDownloadStatus(statusResponseJSON);
    }
  }

  /**
   * Gets the API connection used by this resource.
   *
   * @return the API connection used by this resource.
   */
  public BoxAPIConnection getAPI() {
    return this.api;
  }
}
