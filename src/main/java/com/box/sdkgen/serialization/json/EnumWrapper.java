package com.box.sdkgen.serialization.json;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnumWrapper<E extends Enum<E> & Valuable> implements Valuable {
  private E value;
  private final String stringValue;

  public EnumWrapper(E enumValue) {
    this.stringValue = enumValue.getValue();
    this.value = enumValue;
  }

  public EnumWrapper(String value) {
    this.stringValue = value;
  }

  public E getEnumValue() {
    return value;
  }

  public String getStringValue() {
    return stringValue;
  }

  @Override
  public String getValue() {
    return stringValue;
  }

  @Override
  public String toString() {
    return "EnumWrapper{" + "value=" + value + ", stringValue='" + stringValue + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnumWrapper<?> casted = (EnumWrapper<?>) o;
    return Objects.equals(stringValue, casted.stringValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stringValue);
  }

  public static <T extends Enum<T> & Valuable> List<EnumWrapper<T>> wrapValuableEnumList(
      List<? extends Valuable> listField, Class<T> enumType) {
    if (listField == null || listField.isEmpty()) {
      return Collections.emptyList();
    }
    return listField.stream()
        .map(
            field -> {
              if (field instanceof EnumWrapper) {
                return (EnumWrapper<T>) field;
              } else if (field.getClass().isAssignableFrom(enumType)) {
                return new EnumWrapper<>(enumType.cast(field));
              } else {
                throw new IllegalArgumentException("Unsupported type: " + field.getClass());
              }
            })
        .collect(Collectors.toList());
  }

  public static <T extends Enum<T> & Valuable>
      List<List<EnumWrapper<T>>> wrapValuableEnumListOfLists(
          List<List<? extends Valuable>> listOfLists, Class<T> enumType) {
    if (listOfLists == null || listOfLists.isEmpty()) {
      return Collections.emptyList();
    }
    return listOfLists.stream()
        .map(list -> wrapValuableEnumList(list, enumType))
        .collect(Collectors.toList());
  }

  public static <T extends Enum<T> & Valuable> List<EnumWrapper<T>> wrapListOfEnums(List<T> enums) {
    return enums.stream().map(EnumWrapper::new).collect(Collectors.toList());
  }

  public static <T extends Enum<T> & Valuable> List<List<EnumWrapper<T>>> wrapListOfListsOfEnums(
      List<List<T>> listOfEnums) {
    return listOfEnums.stream().map(EnumWrapper::wrapListOfEnums).collect(Collectors.toList());
  }

  public static <T extends Enum<T> & Valuable> String convertToString(EnumWrapper<T> enumValue) {
    if (enumValue == null) {
      return null;
    }
    return enumValue.getStringValue();
  }

  public static <T extends Enum<T> & Valuable> List<String> convertToString(
      List<EnumWrapper<T>> enumValue) {
    if (enumValue == null) {
      return null;
    }
    return enumValue.stream().map(EnumWrapper::getStringValue).collect(Collectors.toList());
  }
}
