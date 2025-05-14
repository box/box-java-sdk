package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents the download status of a zip file.
 */
public class BoxZipDownloadStatus extends BoxJSONObject {
    private int totalFileCount;
    private int downloadFileCount;
    private int skippedFileCount;
    private int skippedFolderCount;
    private State state;

    /**
     * Constructs a BoxZipDownloadStatus with default settings.
     */
    public BoxZipDownloadStatus() {
    }

    /**
     * Constructs a BoxZipDownloadStatus from a JSON string.
     *
     * @param json the JSON encoded enterprise.
     */
    public BoxZipDownloadStatus(String json) {
        super(json);
    }

    BoxZipDownloadStatus(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the total number of files in the zip.
     *
     * @return the total number of files in the zip.
     */
    public int getTotalFileCount() {
        return this.totalFileCount;
    }

    /**
     * Gets the number of files in the zip that were downloaded.
     *
     * @return the number of files in the zip that were downloaded.
     */
    public int getDownloadFileCount() {
        return this.downloadFileCount;
    }

    /**
     * Gets the number of files in the zip that were skipped when downloading.
     *
     * @return the number of files in the zip that were skipped when downloading.
     */
    public int getSkippedFileCount() {
        return this.skippedFileCount;
    }

    /**
     * Gets the number of folders in the zip that were skipped when downloading.
     *
     * @return the number of folder in the zip that were skipped when downloading.
     */
    public int getSkippedFolderCount() {
        return this.skippedFolderCount;
    }

    /**
     * Gets the state of the download for the zip file.
     *
     * @return the state of the download for the zip file
     */
    public State getState() {
        return this.state;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("total_file_count")) {
            this.totalFileCount = value.asInt();
        } else if (memberName.equals("downloaded_file_count")) {
            this.downloadFileCount = value.asInt();
        } else if (memberName.equals("skipped_file_count")) {
            this.skippedFileCount = value.asInt();
        } else if (memberName.equals("skipped_folder_count")) {
            this.skippedFolderCount = value.asInt();
        } else if (memberName.equals("state")) {
            this.state = State.fromJSONValue(value.asString());
        }
    }

    /**
     * Enumerates the possible download states of a zip.
     */
    public enum State {
        /**
         * Succeeded in downloading.
         */
        SUCCEEDED("succeeded"),

        /**
         * Downloading in progress.
         */
        IN_PROGRESS("in_progress"),

        /**
         * Failed when downloading.
         */
        FAILED("failed");

        private final String jsonValue;

        State(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static State fromJSONValue(String jsonValue) {
            return State.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }
}
