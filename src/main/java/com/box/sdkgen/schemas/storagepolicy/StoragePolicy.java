package com.box.sdkgen.schemas.storagepolicy;

import com.box.sdkgen.schemas.storagepolicymini.StoragePolicyMini;
import com.box.sdkgen.schemas.storagepolicymini.StoragePolicyMiniTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class StoragePolicy extends StoragePolicyMini {

  protected String name;

  public StoragePolicy(@JsonProperty("id") String id) {
    super(id);
  }

  protected StoragePolicy(StoragePolicyBuilder builder) {
    super(builder);
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoragePolicy casted = (StoragePolicy) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "StoragePolicy{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class StoragePolicyBuilder extends StoragePolicyMiniBuilder {

    protected String name;

    public StoragePolicyBuilder(String id) {
      super(id);
    }

    public StoragePolicyBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public StoragePolicyBuilder type(StoragePolicyMiniTypeField type) {
      this.type = new EnumWrapper<StoragePolicyMiniTypeField>(type);
      return this;
    }

    @Override
    public StoragePolicyBuilder type(EnumWrapper<StoragePolicyMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public StoragePolicy build() {
      return new StoragePolicy(this);
    }
  }
}
