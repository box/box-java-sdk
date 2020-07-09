package com.box.sdk;

import com.box.sdk.*;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *
 */
public class BoxZip {
    /**
     * Zip URL Template.
     */
    public static final URLTemplate ZIP_URL_TEMPLATE = new URLTemplate("zip_downloads");
    /**
     * Zip Download URL Template.
     */
    public static final URLTemplate ZIP_DOWNLOAD_URL_TEMPLATE = new URLTemplate("zip_downloads/%s/content");
    private static final int BUFFER_SIZE = 8192;
    private final BoxAPIConnection api;

    /**
     * Constructs a Search to be used by everything.
     *
     * @param api the API connection to be used by the search.
     */
    public BoxZip(BoxAPIConnection api) {
        this.api = api;
    }

    /**
     * Gets an expiring URL for downloading a zip file directly from Box. This can be user,
     * for example, for sending as a redirect to a browser to cause the browser
     * to download the file directly from Box.
     *
     * @return the temporary download zip URL
     */
    public BoxZipInfo create(String name, List<JsonObject> items) {
        JsonArray itemsArray = new JsonArray();
        for (JsonObject item: items) {
            itemsArray.add(item);
        }
        JsonObject requestJSON = new JsonObject();
        requestJSON.add("items", itemsArray);
        requestJSON.add("download_file_name", name);

        URL url = ZIP_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxZipInfo zipInfo = new BoxZipInfo(responseJSON);
        return zipInfo;
    }

    /**
     * Downloads the contents of this file to a given OutputStream.
     *
     * @param output the stream to where the file will be written.
     */
    public BoxZipDownloadStatus download(String name, List<JsonObject> items, OutputStream output) {
        return this.download(name, items, output, null);
    }

    /**
     * Downloads the contents of this file to a given OutputStream
     *
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     * @return the temporary download zip URL
     */
    public BoxZipDownloadStatus download(String name, List<JsonObject> items, OutputStream output, ProgressListener listener) {
        BoxZipInfo zipInfo = create(name, items);

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), zipInfo.getDownloadURL(), "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody(listener);

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        } finally {
            response.disconnect();
        }
        BoxAPIRequest statusRequest = new BoxAPIRequest(this.getAPI(), zipInfo.getStatusURL(), "GET");
        BoxJSONResponse statusResponse = (BoxJSONResponse) statusRequest.send();
        JsonObject statusResponseJSON = JsonObject.readFrom(statusResponse.getJSON());
        BoxZipDownloadStatus downloadStatus = new BoxZipDownloadStatus(statusResponseJSON);
        return downloadStatus;
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
