package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxFileVersion extends BoxResource {
    private String sha1;
    private String name;
    private long size;
    private Date createdAt;
    private Date modifiedAt;
    private BoxUser.Info modifiedBy;

    BoxFileVersion(BoxAPIConnection api, JsonObject jsonObject) {
        super(api, jsonObject.get("id").asString());

        for (JsonObject.Member member : jsonObject) {
            JsonValue value = member.getValue();
            if (value.isNull()) {
                continue;
            }

            try {
                switch (member.getName()) {
                    case "sha1":
                        this.sha1 = value.asString();
                        break;
                    case "name":
                        this.name = value.asString();
                        break;
                    case "size":
                        this.size = Double.valueOf(value.toString()).longValue();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_by":
                        JsonObject userJSON = value.asObject();
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.modifiedBy = user.new Info(userJSON);
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    public String getSha1() {
        return this.sha1;
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    public BoxUser.Info getModifiedBy() {
        return this.modifiedBy;
    }
}
