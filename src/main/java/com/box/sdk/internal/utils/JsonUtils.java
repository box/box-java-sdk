package com.box.sdk.internal.utils;

import com.box.sdk.BoxDateFormat;
import com.eclipsesource.json.JsonObject;
import java.util.Date;

/** Utility class for helping with json related operations. */
public class JsonUtils {

  /** Only static members. */
  protected JsonUtils() {}

  /**
   * Adds String property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(
      JsonObject jsonObject, String propertyName, String propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }

  /**
   * Adds Boolean property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(
      JsonObject jsonObject, String propertyName, Boolean propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }

  /**
   * Adds Integer property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(
      JsonObject jsonObject, String propertyName, Integer propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }

  /**
   * Adds Long property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(JsonObject jsonObject, String propertyName, Long propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }

  /**
   * Adds Enum property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(JsonObject jsonObject, String propertyName, Enum propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue.name().toLowerCase(java.util.Locale.ROOT));
    }
  }

  /**
   * Adds Date property to the json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(JsonObject jsonObject, String propertyName, Date propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, BoxDateFormat.format(propertyValue));
    }
  }

  /**
   * Add JsonObject property to json object if it's not null.
   *
   * @param jsonObject json object that the key/value will be added to.
   * @param propertyName name of the property in json (key).
   * @param propertyValue value of the property.
   */
  public static void addIfNotNull(
      JsonObject jsonObject, String propertyName, JsonObject propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }

  /** Add double property to json object if it's not null. */
  public static void addIfNotNull(
      JsonObject jsonObject, String propertyName, Double propertyValue) {
    if (propertyValue != null) {
      jsonObject.add(propertyName, propertyValue);
    }
  }
}
