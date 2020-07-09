package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents items that have naming conflicts when creating a zip file.
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
     * Gets the ID of the item that has the conflict.
     *
     * @return the ID of the item that has the conflict.
     */
    public int getTotalFileCount() {
        return this.totalFileCount;
    }

    /**
     * Gets the type of the item that has the conflict.
     *
     * @return the type of the item that has the conflict.
     */
    public int getDownloadFileCount() {
        return this.downloadFileCount;
    }

    /**
     * Gets the original name of the item that has the conflict.
     *
     * @return the original name of the item that has the conflict.
     */
    public int getSkippedFileCount() {
        return this.skippedFileCount;
    }

    /**
     * Gets the new name of the item when it downloads that resolves the conflict.
     *
     * @return the new name of the item when it downloads that resolves the conflict.
     */
    public int getSkippedFolderCount() {
        return this.skippedFolderCount;
    }

    /**
     * Gets the new name of the item when it downloads that resolves the conflict.
     *
     * @return the new name of the item when it downloads that resolves the conflict.
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
        } else if (memberName.equals("download_file_count")) {
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
     * Enumerates the possible access levels that can be set on an upload email.
     */
    public enum State {
        /**
         * Anyone can send an upload to this email address.
         */
        SUCCEEDED("succeeded"),

        /**
         * Only collaborators can send an upload to this email address.
         */
        IN_PROGRESS("in_progress"),

        /**
         * Only collaborators can send an upload to this email address.
         */
        FAILED("failed");

        private final String jsonValue;

        private State(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static State fromJSONValue(String jsonValue) {
            return State.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }
}
