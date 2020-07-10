package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents items that have naming conflicts when creating a zip file.
 */
public class BoxZipInfo extends BoxJSONObject {
    private URL downloadURL;
    private URL statusURL;
    private Date expiresAt;
    private List<BoxZipConflict> nameConflicts;

    /**
     * Constructs a BoxZipDownloadStatus with default settings.
     */
    public BoxZipInfo() {
    }

    /**
     * Constructs a BoxZipDownloadStatus from a JSON string.
     *
     * @param json the JSON encoded enterprise.
     */
    public BoxZipInfo(String json) {
        super(json);
    }

    BoxZipInfo(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the ID of the item that has the conflict.
     *
     * @return the ID of the item that has the conflict.
     */
    public URL getDownloadURL() {
        return this.downloadURL;
    }

    /**
     * Gets the type of the item that has the conflict.
     *
     * @return the type of the item that has the conflict.
     */
    public URL getStatusURL() {
        return this.statusURL;
    }

    /**
     * Gets the original name of the item that has the conflict.
     *
     * @return the original name of the item that has the conflict.
     */
    public Date getExpiresAt() {
        return this.expiresAt;
    }

    /**
     * Gets the new name of the item when it downloads that resolves the conflict.
     *
     * @return the new name of the item when it downloads that resolves the conflict.
     */
    public List<BoxZipConflict> getNameConflicts() {
        return this.nameConflicts;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if (memberName.equals("download_url")) {
                try {
                    String urlString = value.asString();
                    this.downloadURL = new URL(urlString);
                } catch (MalformedURLException e) {
                    throw new BoxAPIException("Couldn't parse download url for zip", e);
                }
            } else if (memberName.equals("status_url")) {
                try {
                    String urlString = value.asString();
                    this.statusURL = new URL(urlString);
                } catch (MalformedURLException e) {
                    throw new BoxAPIException("Couldn't parse status url for zip", e);
                }
            } else if (memberName.equals("expires_at")) {
                this.expiresAt = BoxDateFormat.parse(value.asString());
            } else if (memberName.equals("name_conflicts")) {
                this.nameConflicts = this.parseNameConflicts(value.asArray());
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }

    private List<BoxZipConflict> parseNameConflicts(JsonArray jsonArray) {
        List<BoxZipConflict> nameConflicts = new ArrayList<BoxZipConflict>(jsonArray.size());
        for (JsonValue conflict : jsonArray) {
            // Must create a conflict object with an arbitrary key like "conflict" to allow BoxZipConflict
            // to read the object
            JsonObject conflictObj = new JsonObject().add("conflict", conflict);
            nameConflicts.add(new BoxZipConflict(conflictObj));
        }
        return nameConflicts;
    }
}

