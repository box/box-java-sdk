package com.box.sdkgen.schemas.devicepinners;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.devicepinner.DevicePinner;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class DevicePinners extends SerializableObject {

  protected List<DevicePinner> entries;

  protected Long limit;

  @JsonProperty("next_marker")
  protected Long nextMarker;

  protected List<DevicePinnersOrderField> order;

  public DevicePinners() {
    super();
  }

  protected DevicePinners(DevicePinnersBuilder builder) {
    super();
    this.entries = builder.entries;
    this.limit = builder.limit;
    this.nextMarker = builder.nextMarker;
    this.order = builder.order;
  }

  public List<DevicePinner> getEntries() {
    return entries;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getNextMarker() {
    return nextMarker;
  }

  public List<DevicePinnersOrderField> getOrder() {
    return order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevicePinners casted = (DevicePinners) o;
    return Objects.equals(entries, casted.entries)
        && Objects.equals(limit, casted.limit)
        && Objects.equals(nextMarker, casted.nextMarker)
        && Objects.equals(order, casted.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries, limit, nextMarker, order);
  }

  @Override
  public String toString() {
    return "DevicePinners{"
        + "entries='"
        + entries
        + '\''
        + ", "
        + "limit='"
        + limit
        + '\''
        + ", "
        + "nextMarker='"
        + nextMarker
        + '\''
        + ", "
        + "order='"
        + order
        + '\''
        + "}";
  }

  public static class DevicePinnersBuilder {

    protected List<DevicePinner> entries;

    protected Long limit;

    protected Long nextMarker;

    protected List<DevicePinnersOrderField> order;

    public DevicePinnersBuilder entries(List<DevicePinner> entries) {
      this.entries = entries;
      return this;
    }

    public DevicePinnersBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public DevicePinnersBuilder nextMarker(Long nextMarker) {
      this.nextMarker = nextMarker;
      return this;
    }

    public DevicePinnersBuilder order(List<DevicePinnersOrderField> order) {
      this.order = order;
      return this;
    }

    public DevicePinners build() {
      return new DevicePinners(this);
    }
  }
}
