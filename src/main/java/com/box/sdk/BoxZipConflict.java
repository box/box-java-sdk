package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.ArrayList;
import java.util.List;

/** Represents a conflict that occurs between items that have the same name. */
public class BoxZipConflict extends BoxJSONObject {
  private List<BoxZipConflictItem> items;

  /** Constructs a BoxZipDownloadStatus with default settings. */
  public BoxZipConflict() {}

  /**
   * Constructs a BoxZipDownloadStatus from a JSON string.
   *
   * @param json the JSON encoded enterprise.
   */
  public BoxZipConflict(String json) {
    super(json);
  }

  BoxZipConflict(JsonObject jsonObject) {
    super(jsonObject);
  }

  /**
   * Gets the items that conflict because they have the same name.
   *
   * @return the items that conflict because they have the same name.
   */
  public List<BoxZipConflictItem> getItems() {
    return this.items;
  }

  @Override
  void parseJSONMember(JsonObject.Member member) {
    JsonArray value = member.getValue().asArray();
    List<BoxZipConflictItem> conflictItems = new ArrayList<BoxZipConflictItem>(value.size());
    for (JsonValue item : value) {
      conflictItems.add(new BoxZipConflictItem(item.asObject()));
    }
    this.items = conflictItems;
  }
}
