package com.box.sdkgen.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;

public abstract class NullableFieldTracker {

  @JsonIgnore protected final Set<String> explicitlySetNullableFields = new HashSet<>();

  protected void markNullableFieldAsSet(String fieldName) {
    explicitlySetNullableFields.add(fieldName);
  }

  protected void markNullableFieldsAsSet(Set<String> fields) {
    explicitlySetNullableFields.addAll(fields);
  }

  protected boolean isFieldExplicitlySet(String fieldName) {
    return explicitlySetNullableFields.contains(fieldName);
  }

  public Set<String> getExplicitlySetNullableFields() {
    return explicitlySetNullableFields;
  }
}
