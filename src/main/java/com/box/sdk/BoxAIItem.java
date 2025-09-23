package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/** Represents a Box File to be included in a sign request. */
public class BoxAIItem {
  private String id;
  private Type type;
  private String content;

  /**
   * Created a BoxAIItem - the item to be processed by the LLM.
   *
   * @param id The id of the item
   * @param type The type of the item. Currently, only "file" is supported.
   * @param content The content of the item, often the text representation.
   */
  public BoxAIItem(String id, Type type, String content) {
    this.id = id;
    this.type = type;
    this.content = content;
  }

  /**
   * Created a BoxAIItem - the item to be processed by the LLM.
   *
   * @param id The id of the item
   * @param type The type of the item. Currently, only "file" is supported.
   */
  public BoxAIItem(String id, Type type) {
    this.id = id;
    this.type = type;
  }

  /**
   * Gets the id of the item.
   *
   * @return the id of the item.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id of the item.
   *
   * @param id the id of the item.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the type of the item.
   *
   * @return the type of the item.
   */
  public Type getType() {
    return type;
  }

  /**
   * Sets the type of the item.
   *
   * @param type the type of the item.
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * Gets the content of the item.
   *
   * @return the content of the item.
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of the item.
   *
   * @param content the content of the item.
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Gets a JSON object representing this class.
   *
   * @return the JSON object representing this class.
   */
  public JsonObject getJSONObject() {
    JsonObject itemJSON = new JsonObject().add("id", this.id).add("type", this.type.toJSONValue());

    if (this.content != null) {
      itemJSON.add("content", this.content);
    }

    return itemJSON;
  }

  public enum Type {
    /** A file. */
    FILE("file");

    private final String jsonValue;

    Type(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    static BoxAIItem.Type fromJSONValue(String jsonValue) {
      return BoxAIItem.Type.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
    }

    String toJSONValue() {
      return this.jsonValue;
    }
  }
}
